package com.javahub.springcore.service;

import com.javahub.springcore.model.PrototypeReport;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportCoordinatorService {

    private final ObjectProvider<PrototypeReport> reportProvider;

    public ReportCoordinatorService(ObjectProvider<PrototypeReport> reportProvider) {
        this.reportProvider = reportProvider;
    }

    public List<String> produceTwoReports() {
        PrototypeReport r1 = reportProvider.getObject();
        PrototypeReport r2 = reportProvider.getObject();

        return List.of(
            r1.generate("Financial Q3"),
            r2.generate("Operations Q3")
        );
    }
}
