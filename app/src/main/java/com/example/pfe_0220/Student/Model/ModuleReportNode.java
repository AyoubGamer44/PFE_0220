package com.example.pfe_0220.Student.Model;

import androidx.room.Ignore;

import java.util.ArrayList;
import java.util.List;

public class ModuleReportNode {

    public String moduleName;
     public int moduleId;
    @Ignore
    public List<AttendenceReportNode> attendenceReportNodes;

    public ModuleReportNode(String moduleName, int moduleId) {
        this.moduleName = moduleName;
        this.moduleId = moduleId;
    }
}

