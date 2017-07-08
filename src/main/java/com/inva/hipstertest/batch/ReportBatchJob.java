package com.inva.hipstertest.batch;

import com.inva.hipstertest.service.PupilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by slavkosoltys on 08.07.17.
 */
@Component
public class ReportBatchJob {
    @Autowired
    private PupilService pupilService;

    @Scheduled(cron = "0,10 * * * * *") // use https://crontab-generator.org/ for expression
    public void run() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>> JOB <<<<<<<<<<<<<<<<<<<<<<");

        // calculate average mark and store into db if needed


        //pupilService.getMarksByPupilId();
    }
}
