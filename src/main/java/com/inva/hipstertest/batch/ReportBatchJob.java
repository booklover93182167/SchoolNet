package com.inva.hipstertest.batch;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by slavkosoltys on 14.07.17.
 */
@Component
public class ReportBatchJob {




    @Scheduled(cron = "0,10 * * * * *")
    public void run(){
        System.out.println(">>>>>>>>>>>>>>>>>>>>> JOB <<<<<<<<<<<<<<<<<<<<<<");
    }


}
