package com.stdio.mvvmsample2.repository;

import androidx.lifecycle.LiveData;

import com.stdio.mvvmsample2.models.ApiResponse;

public interface IssueRepository {

    LiveData<ApiResponse> getIssues(String owner, String repo);
}
