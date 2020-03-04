package com.viger.screenadapter;

import com.viger.screenadapter.plan_one.GenerateValueFiles;

public class GenerateValuesFileUtils {

    public static void main(String[] args) {
        new GenerateValueFiles(GenerateValueFiles.base_width, GenerateValueFiles.base_height, "").generate();
    }

}
