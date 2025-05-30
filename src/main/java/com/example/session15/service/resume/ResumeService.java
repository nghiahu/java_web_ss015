package com.example.session15.service.resume;

import com.example.session15.model.Resume;

import java.util.List;

public interface ResumeService {
    boolean addResume(Resume resume);
    List<Resume> getAllResumes();
    boolean removeResume(int id);
    Resume getResume(int id);
    boolean updateResume(Resume resume);
}
