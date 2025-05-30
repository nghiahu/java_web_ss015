package com.example.session15.repsitory.resume;

import com.example.session15.model.Resume;

import java.util.List;

public interface ResumeRepository {
    boolean addResume(Resume resume);
    List<Resume> getAllResumes();
    boolean removeResume(int id);
    Resume getResumeId(int id);
    boolean updateResume(Resume resume);
}
