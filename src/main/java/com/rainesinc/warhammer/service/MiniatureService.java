package com.rainesinc.warhammer.service;

import com.rainesinc.warhammer.repository.MiniatureRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MiniatureService {
    @Autowired
    private MiniatureRepository repo;
}
