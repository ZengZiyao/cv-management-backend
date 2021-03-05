package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dto.CitationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CitationService {
    List<CitationDto> getAllCitations(String userid);

    void updateCitation(String id, CitationDto citationDto);

    void createCitations(String userid);

    void deleteCitationsByUser(String userid);

    void updateGSCitation(String userid, CitationDto citationDto);
}
