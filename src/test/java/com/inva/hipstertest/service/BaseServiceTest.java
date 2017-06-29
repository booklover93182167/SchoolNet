package com.inva.hipstertest.service;

import com.inva.hipstertest.data.provider.FormProvider;
import com.inva.hipstertest.data.provider.SchoolProvider;
import com.inva.hipstertest.data.provider.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by slavkosoltys on 30.06.17.
 */
@Component
public abstract class BaseServiceTest {

    @Autowired
    protected UserProvider userProvider;

    @Autowired
    protected SchoolProvider schoolProvider;

    @Autowired
    protected FormProvider formProvider;
}
