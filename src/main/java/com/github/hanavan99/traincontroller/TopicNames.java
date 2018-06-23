package com.github.hanavan99.traincontroller;

public class TopicNames {
    public static final String DEVICE_TOPIC = "/raw/%s";
    public static final String OBJECT_ID = "/%s/%s/id";
    public static final String OBJECT_NAME = "/%s/%s/name";
    public static final String OBJECT_PROPS = "/%s/%s/props";
    public static final String THROW_ROUTE = "/route/%s/throw";
    public static final String THROW_SWITCH = "/switch/%s/throw";
    public static final String ENGINE_COMMAND = "/engine/%s/command/%s";
    public static final String ENGINE_START_COMMAND = "/engine/%s/start/%s";
    public static final String ENGINE_END_COMMAND = "/engine/%s/end/%s";
    public static final String OBJECT_NEW = "/%s/.new";
    public static final String OBJECT_DELETE = "/%s/%s/delete";
    public static final String SWITCH_ADD_THROUGH = "/switch/%s/add_route/through";
    public static final String SWITCH_ADD_OUT = "/switch/%s/add_route/out";
    public static final String CLEAR_ROUTE = "/route/%s/clear";
    public static final String OBJECT_LIST = "/%s/.list";
}
