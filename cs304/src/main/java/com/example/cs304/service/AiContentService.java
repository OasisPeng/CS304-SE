package com.example.cs304.service;

import com.example.cs304.entity.AiContent;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface AiContentService {
    public AiContent SelectById(int id);
    public List<AiContent> SelectByUserName(int user);
    public Integer DeleteById(int id);
}
