package com.zzy.cvmanagementsystem.taskscheduler;

import com.zzy.cvmanagementsystem.dto.CitationDto;
import com.zzy.cvmanagementsystem.dto.UserDto;
import com.zzy.cvmanagementsystem.service.CitationService;
import com.zzy.cvmanagementsystem.service.UserService;
import com.zzy.cvmanagementsystem.service.impl.CitationServiceImpl;
import com.zzy.cvmanagementsystem.service.impl.UserServiceImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class ThreadPoolTaskSchedule {
    private ThreadPoolTaskScheduler taskScheduler;
    private PeriodicTrigger periodicTrigger;
    private UserService userService;
    private CitationService citationService;

    ThreadPoolTaskSchedule(ThreadPoolTaskScheduler taskScheduler, PeriodicTrigger periodicTrigger, UserServiceImpl userService, CitationServiceImpl citationService) {
        this.taskScheduler = taskScheduler;
        this.periodicTrigger = periodicTrigger;
        this.userService = userService;
        this.citationService = citationService;
    }

    @PostConstruct
    public void scheduleRunnableWithCronTrigger() {
        taskScheduler.schedule(new RunnableTask("Periodic Trigger"), periodicTrigger);
    }

    class RunnableTask implements Runnable {

        private String message;

        public RunnableTask(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            List<UserDto> userDtoList = userService.getAllUsersWithGS();
            for (UserDto userDto :
                    userDtoList) {
                List<Integer> results = crawlCitation(userDto.getGsAuthorId());
                if (results.size() == 2) {
                    CitationDto citationDto = new CitationDto();
                    citationDto.setCountWithSelf(results.get(0));
                    citationDto.setHindex(results.get(1));
                    citationService.updateGSCitation(userDto.getId(), citationDto);
                }
            }

        }

        private List<Integer> crawlCitation(String authorId) {
            List<Integer> result = new ArrayList<>();

            try {
                File f = new File("src/main/java/com/zzy/cvmanagementsystem/script/crawl.py");
                ProcessBuilder processBuilder = new ProcessBuilder("python3", f.getAbsolutePath(), "https://scholar.google.com/citations?user=" + authorId);
                processBuilder.redirectErrorStream(true);

                Process process = processBuilder.start();
                BufferedReader bfr = new BufferedReader(new InputStreamReader(process.getInputStream()));
                process.waitFor();
                String line;
                while ((line = bfr.readLine()) != null) {
                    result.add(Integer.parseInt(line));
                }
            } catch (Exception e) {
                System.out.println(e);
            }

            return result;
        }
    }
}
