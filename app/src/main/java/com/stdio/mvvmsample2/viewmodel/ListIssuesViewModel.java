package com.stdio.mvvmsample2.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.stdio.mvvmsample2.models.ApiResponse;
import com.stdio.mvvmsample2.repository.IssueRepository;
import com.stdio.mvvmsample2.repository.IssueRepositoryImpl;

public class ListIssuesViewModel extends ViewModel {

    private MediatorLiveData<ApiResponse> apiResponse;
    private IssueRepository issueRepository;

    public ListIssuesViewModel() {
        apiResponse = new MediatorLiveData<>();
        issueRepository = new IssueRepositoryImpl();
    }

    @NonNull
    public LiveData<ApiResponse> getApiResponse() {
        return apiResponse;
    }

    public void loadIssues(@NonNull String user, String repo) {
        LiveData<ApiResponse> issuesSource = issueRepository.getIssues(user, repo);
        apiResponse.addSource(
                issuesSource,
                apiResponse -> {
                    if (this.apiResponse.hasActiveObservers()) {
                        this.apiResponse.removeSource(issuesSource);
                    }
                    this.apiResponse.setValue(apiResponse);
                }
        );
    }

}