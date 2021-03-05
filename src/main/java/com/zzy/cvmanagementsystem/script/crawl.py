#!/usr/bin/env python3

import argparse
import logging
import math
import os.path
import sys
import time

import requests
from bs4 import BeautifulSoup

REQUEST_HEADERS = {"User-Agent": "Innocent Browser", "Accept-Charset": "UTF-8,*;q=0.5"}

parser = argparse.ArgumentParser(description='To retrieve all of your citations from Google Scholar.')
parser.add_argument('google_scholar_uri', type=str, help='Your google scholar homepage')
parser.add_argument('--request-interval', action='store', type=int, default=100,
                    help='Interval (in seconds) between requests to google scholar')
opts = parser.parse_args()

session = requests.Session()


def get_total_citations_num(soup):
    """
    Get the total citation number from user's google scholar homepage
    """
    total_citations_num = int(soup("td", {"class": "gsc_rsb_std"})[0].getText())
    return total_citations_num

def get_h_index_num(soup):
    h_index_num = int(soup("td", {"class": "gsc_rsb_std"})[2].getText())
    return h_index_num


def create_soup_by_url(page_url, params=None):
    '''
    helper function to create a beautiful soup object with given URL and parameters
    '''
    try:
        time.sleep(opts.request_interval)
        res = session.get(page_url, params=params, headers=REQUEST_HEADERS, timeout=10)
        res.encoding = 'UTF-8'
        logging.debug("Creating soup for URL: %s" % res.url)
        if res.status_code != 200:
            logging.debug("Response text: %s" % res.text)
            raise Exception("Bad response status code %d" % res.status_code)
        soup = BeautifulSoup(res.text, "html.parser")
        if soup.h1 and soup.h1.text == "Please show you're not a robot":
            raise Exception("You need to verify manually that you're not a robot.")
        return soup
    except Exception as err:
        logging.error("Can't open link: " + page_url + " Error: " + str(err))
        sys.exit(1)


def main():
    soup = create_soup_by_url(opts.google_scholar_uri)
    total_num = get_total_citations_num(soup)
    h_index_num = get_h_index_num(soup)
    print(total_num)
    print(h_index_num)


if __name__ == "__main__":
    sys.exit(main())
