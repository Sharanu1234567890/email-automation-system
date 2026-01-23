package com.email_scheduler.api_service.service;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CsvParserService {

    public List<String> parse(List<String> emails) {
        return emails;
    }
}
