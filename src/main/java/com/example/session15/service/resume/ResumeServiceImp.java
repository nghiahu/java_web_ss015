package com.example.session15.service.resume;

import com.example.session15.model.Resume;
import com.example.session15.repsitory.resume.ResumeRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeServiceImp implements ResumeService {
    @Autowired
    private ResumeRepositoryImp resumeRepositoryImp;

    @Override
    public boolean addResume(Resume resume) {
        return resumeRepositoryImp.addResume(resume);
    }

    @Override
    public List<Resume> getAllResumes() {
        return resumeRepositoryImp.getAllResumes();
    }

    @Override
    public boolean removeResume(int id) {
        return resumeRepositoryImp.removeResume(id);
    }

    @Override
    public Resume getResume(int id) {
        return resumeRepositoryImp.getResumeId(id);
    }

    @Override
    public boolean updateResume(Resume resume) {
        return resumeRepositoryImp.updateResume(resume);
    }
}
