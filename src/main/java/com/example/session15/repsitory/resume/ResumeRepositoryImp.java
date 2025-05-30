package com.example.session15.repsitory.resume;

import com.example.session15.config.ConnectionDB;
import com.example.session15.model.Resume;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ResumeRepositoryImp implements ResumeRepository {

    @Override
    public boolean addResume(Resume resume) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call saveResume(?,?,?,?,?,?,?)}");
            callSt.setString(1, resume.getFullName());
            callSt.setString(2, resume.getEmail());
            callSt.setString(3, resume.getPhoneNumber());
            callSt.setString(4, resume.getEducation());
            callSt.setString(5, resume.getExperience());
            callSt.setString(6, resume.getSkills());
            callSt.setString(7, resume.getImageUrl());
            callSt.execute();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public List<Resume> getAllResumes() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Resume> resumes = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call getAllResume()}");
            callSt.execute();
            ResultSet resultSet = callSt.getResultSet();
            while (resultSet.next()) {
                Resume resume = new Resume();
                resume.setId(resultSet.getInt("id"));
                resume.setFullName(resultSet.getString("fullName"));
                resume.setEmail(resultSet.getString("email"));
                resume.setPhoneNumber(resultSet.getString("phoneNumber"));
                resume.setEducation(resultSet.getString("education"));
                resume.setExperience(resultSet.getString("experience"));
                resume.setSkills(resultSet.getString("skills"));
                resume.setImageUrl(resultSet.getString("image"));

                resumes.add(resume);
            }
            return resumes;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return resumes;
    }

    @Override
    public boolean removeResume(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call removeResume(?)}");
            callSt.setInt(1, id);
            callSt.execute();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public Resume getResumeId(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Resume resume = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call getResumeById(?)}");
            callSt.setInt(1, id);
            callSt.execute();
            ResultSet resultSet = callSt.getResultSet();
            if (resultSet.next()) {
                resume = new Resume();
                resume.setId(resultSet.getInt("id"));
                resume.setFullName(resultSet.getString("fullName"));
                resume.setEmail(resultSet.getString("email"));
                resume.setPhoneNumber(resultSet.getString("phoneNumber"));
                resume.setEducation(resultSet.getString("education"));
                resume.setExperience(resultSet.getString("experience"));
                resume.setSkills(resultSet.getString("skills"));
                resume.setImageUrl(resultSet.getString("image"));
                return resume;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return resume;
    }

    @Override
    public boolean updateResume(Resume resume) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call updateResume(?,?,?,?,?,?,?,?)}");
            callSt.setInt(1, resume.getId());
            callSt.setString(2, resume.getFullName());
            callSt.setString(3, resume.getEmail());
            callSt.setString(4, resume.getPhoneNumber());
            callSt.setString(5, resume.getEducation());
            callSt.setString(6, resume.getExperience());
            callSt.setString(7, resume.getSkills());
            callSt.setString(8, resume.getImageUrl());
            callSt.execute();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }
}
