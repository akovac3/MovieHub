// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: EventService.proto

package com.moviehub.systemevents.event;

public final class EventServiceOuterClass {
  private EventServiceOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_EventRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_EventRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_EventResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_EventResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\022EventService.proto\032\037google/protobuf/ti" +
      "mestamp.proto\"\343\001\n\014EventRequest\022(\n\004date\030\001" +
      " \001(\0132\032.google.protobuf.Timestamp\022\024\n\014micr" +
      "oservice\030\002 \001(\t\022\014\n\004user\030\003 \001(\t\022(\n\006action\030\004" +
      " \001(\0162\030.EventRequest.actionType\022\020\n\010resour" +
      "ce\030\005 \001(\t\022\016\n\006status\030\006 \001(\t\"9\n\nactionType\022\n" +
      "\n\006CREATE\020\000\022\n\n\006DELETE\020\001\022\007\n\003GET\020\002\022\n\n\006UPDAT" +
      "E\020\003\"0\n\rEventResponse\022\017\n\007message\030\001 \001(\t\022\016\n" +
      "\006status\030\002 \001(\t24\n\014EventService\022$\n\003log\022\r.E" +
      "ventRequest\032\016.EventResponseB\034\n\030com.quizh" +
      "ub.systemeventsP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.TimestampProto.getDescriptor(),
        });
    internal_static_EventRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_EventRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_EventRequest_descriptor,
        new String[] { "Date", "Microservice", "User", "Action", "Resource", "Status", });
    internal_static_EventResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_EventResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_EventResponse_descriptor,
        new String[] { "Message", "Status", });
    com.google.protobuf.TimestampProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
