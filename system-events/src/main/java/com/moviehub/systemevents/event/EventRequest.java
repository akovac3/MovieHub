// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: EventService.proto

package com.moviehub.systemevents.event;

/**
 * Protobuf type {@code EventRequest}
 */
public final class EventRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:EventRequest)
        EventRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use EventRequest.newBuilder() to construct.
  private EventRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private EventRequest() {
    microservice_ = "";
    user_ = "";
    action_ = 0;
    resource_ = "";
    status_ = "";
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new EventRequest();
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private EventRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            com.google.protobuf.Timestamp.Builder subBuilder = null;
            if (date_ != null) {
              subBuilder = date_.toBuilder();
            }
            date_ = input.readMessage(com.google.protobuf.Timestamp.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(date_);
              date_ = subBuilder.buildPartial();
            }

            break;
          }
          case 18: {
            String s = input.readStringRequireUtf8();

            microservice_ = s;
            break;
          }
          case 26: {
            String s = input.readStringRequireUtf8();

            user_ = s;
            break;
          }
          case 32: {
            int rawValue = input.readEnum();

            action_ = rawValue;
            break;
          }
          case 42: {
            String s = input.readStringRequireUtf8();

            resource_ = s;
            break;
          }
          case 50: {
            String s = input.readStringRequireUtf8();

            status_ = s;
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return EventServiceOuterClass.internal_static_EventRequest_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return EventServiceOuterClass.internal_static_EventRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            EventRequest.class, EventRequest.Builder.class);
  }

  /**
   * Protobuf enum {@code EventRequest.actionType}
   */
  public enum actionType
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>CREATE = 0;</code>
     */
    CREATE(0),
    /**
     * <code>DELETE = 1;</code>
     */
    DELETE(1),
    /**
     * <code>GET = 2;</code>
     */
    GET(2),
    /**
     * <code>UPDATE = 3;</code>
     */
    UPDATE(3),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>CREATE = 0;</code>
     */
    public static final int CREATE_VALUE = 0;
    /**
     * <code>DELETE = 1;</code>
     */
    public static final int DELETE_VALUE = 1;
    /**
     * <code>GET = 2;</code>
     */
    public static final int GET_VALUE = 2;
    /**
     * <code>UPDATE = 3;</code>
     */
    public static final int UPDATE_VALUE = 3;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @Deprecated
    public static actionType valueOf(int value) {
      return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static actionType forNumber(int value) {
      switch (value) {
        case 0: return CREATE;
        case 1: return DELETE;
        case 2: return GET;
        case 3: return UPDATE;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<actionType>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        actionType> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<actionType>() {
            public actionType findValueByNumber(int number) {
              return actionType.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      if (this == UNRECOGNIZED) {
        throw new IllegalStateException(
            "Can't get the descriptor of an unrecognized enum value.");
      }
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return EventRequest.getDescriptor().getEnumTypes().get(0);
    }

    private static final actionType[] VALUES = values();

    public static actionType valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private actionType(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:EventRequest.actionType)
  }

  public static final int DATE_FIELD_NUMBER = 1;
  private com.google.protobuf.Timestamp date_;
  /**
   * <code>.google.protobuf.Timestamp date = 1;</code>
   * @return Whether the date field is set.
   */
  @Override
  public boolean hasDate() {
    return date_ != null;
  }
  /**
   * <code>.google.protobuf.Timestamp date = 1;</code>
   * @return The date.
   */
  @Override
  public com.google.protobuf.Timestamp getDate() {
    return date_ == null ? com.google.protobuf.Timestamp.getDefaultInstance() : date_;
  }
  /**
   * <code>.google.protobuf.Timestamp date = 1;</code>
   */
  @Override
  public com.google.protobuf.TimestampOrBuilder getDateOrBuilder() {
    return getDate();
  }

  public static final int MICROSERVICE_FIELD_NUMBER = 2;
  private volatile Object microservice_;
  /**
   * <code>string microservice = 2;</code>
   * @return The microservice.
   */
  @Override
  public String getMicroservice() {
    Object ref = microservice_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      microservice_ = s;
      return s;
    }
  }
  /**
   * <code>string microservice = 2;</code>
   * @return The bytes for microservice.
   */
  @Override
  public com.google.protobuf.ByteString
      getMicroserviceBytes() {
    Object ref = microservice_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      microservice_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int USER_FIELD_NUMBER = 3;
  private volatile Object user_;
  /**
   * <code>string user = 3;</code>
   * @return The user.
   */
  @Override
  public String getUser() {
    Object ref = user_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      user_ = s;
      return s;
    }
  }
  /**
   * <code>string user = 3;</code>
   * @return The bytes for user.
   */
  @Override
  public com.google.protobuf.ByteString
      getUserBytes() {
    Object ref = user_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      user_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ACTION_FIELD_NUMBER = 4;
  private int action_;
  /**
   * <code>.EventRequest.actionType action = 4;</code>
   * @return The enum numeric value on the wire for action.
   */
  @Override public int getActionValue() {
    return action_;
  }
  /**
   * <code>.EventRequest.actionType action = 4;</code>
   * @return The action.
   */
  @Override public EventRequest.actionType getAction() {
    @SuppressWarnings("deprecation")
    EventRequest.actionType result = EventRequest.actionType.valueOf(action_);
    return result == null ? EventRequest.actionType.UNRECOGNIZED : result;
  }

  public static final int RESOURCE_FIELD_NUMBER = 5;
  private volatile Object resource_;
  /**
   * <code>string resource = 5;</code>
   * @return The resource.
   */
  @Override
  public String getResource() {
    Object ref = resource_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      resource_ = s;
      return s;
    }
  }
  /**
   * <code>string resource = 5;</code>
   * @return The bytes for resource.
   */
  @Override
  public com.google.protobuf.ByteString
      getResourceBytes() {
    Object ref = resource_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      resource_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int STATUS_FIELD_NUMBER = 6;
  private volatile Object status_;
  /**
   * <code>string status = 6;</code>
   * @return The status.
   */
  @Override
  public String getStatus() {
    Object ref = status_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      status_ = s;
      return s;
    }
  }
  /**
   * <code>string status = 6;</code>
   * @return The bytes for status.
   */
  @Override
  public com.google.protobuf.ByteString
      getStatusBytes() {
    Object ref = status_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      status_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (date_ != null) {
      output.writeMessage(1, getDate());
    }
    if (!getMicroserviceBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, microservice_);
    }
    if (!getUserBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, user_);
    }
    if (action_ != EventRequest.actionType.CREATE.getNumber()) {
      output.writeEnum(4, action_);
    }
    if (!getResourceBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, resource_);
    }
    if (!getStatusBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 6, status_);
    }
    unknownFields.writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (date_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getDate());
    }
    if (!getMicroserviceBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, microservice_);
    }
    if (!getUserBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, user_);
    }
    if (action_ != EventRequest.actionType.CREATE.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(4, action_);
    }
    if (!getResourceBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, resource_);
    }
    if (!getStatusBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, status_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof EventRequest)) {
      return super.equals(obj);
    }
    EventRequest other = (EventRequest) obj;

    if (hasDate() != other.hasDate()) return false;
    if (hasDate()) {
      if (!getDate()
          .equals(other.getDate())) return false;
    }
    if (!getMicroservice()
        .equals(other.getMicroservice())) return false;
    if (!getUser()
        .equals(other.getUser())) return false;
    if (action_ != other.action_) return false;
    if (!getResource()
        .equals(other.getResource())) return false;
    if (!getStatus()
        .equals(other.getStatus())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasDate()) {
      hash = (37 * hash) + DATE_FIELD_NUMBER;
      hash = (53 * hash) + getDate().hashCode();
    }
    hash = (37 * hash) + MICROSERVICE_FIELD_NUMBER;
    hash = (53 * hash) + getMicroservice().hashCode();
    hash = (37 * hash) + USER_FIELD_NUMBER;
    hash = (53 * hash) + getUser().hashCode();
    hash = (37 * hash) + ACTION_FIELD_NUMBER;
    hash = (53 * hash) + action_;
    hash = (37 * hash) + RESOURCE_FIELD_NUMBER;
    hash = (53 * hash) + getResource().hashCode();
    hash = (37 * hash) + STATUS_FIELD_NUMBER;
    hash = (53 * hash) + getStatus().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static EventRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static EventRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static EventRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static EventRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static EventRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static EventRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static EventRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static EventRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static EventRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static EventRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static EventRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static EventRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(EventRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code EventRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:EventRequest)
          EventRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return EventServiceOuterClass.internal_static_EventRequest_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return EventServiceOuterClass.internal_static_EventRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              EventRequest.class, EventRequest.Builder.class);
    }

    // Construct using com.quizhub.systemevents.EventRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @Override
    public Builder clear() {
      super.clear();
      if (dateBuilder_ == null) {
        date_ = null;
      } else {
        date_ = null;
        dateBuilder_ = null;
      }
      microservice_ = "";

      user_ = "";

      action_ = 0;

      resource_ = "";

      status_ = "";

      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return EventServiceOuterClass.internal_static_EventRequest_descriptor;
    }

    @Override
    public EventRequest getDefaultInstanceForType() {
      return EventRequest.getDefaultInstance();
    }

    @Override
    public EventRequest build() {
      EventRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public EventRequest buildPartial() {
      EventRequest result = new EventRequest(this);
      if (dateBuilder_ == null) {
        result.date_ = date_;
      } else {
        result.date_ = dateBuilder_.build();
      }
      result.microservice_ = microservice_;
      result.user_ = user_;
      result.action_ = action_;
      result.resource_ = resource_;
      result.status_ = status_;
      onBuilt();
      return result;
    }

    @Override
    public Builder clone() {
      return super.clone();
    }
    @Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return super.setField(field, value);
    }
    @Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return super.addRepeatedField(field, value);
    }
    @Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof EventRequest) {
        return mergeFrom((EventRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(EventRequest other) {
      if (other == EventRequest.getDefaultInstance()) return this;
      if (other.hasDate()) {
        mergeDate(other.getDate());
      }
      if (!other.getMicroservice().isEmpty()) {
        microservice_ = other.microservice_;
        onChanged();
      }
      if (!other.getUser().isEmpty()) {
        user_ = other.user_;
        onChanged();
      }
      if (other.action_ != 0) {
        setActionValue(other.getActionValue());
      }
      if (!other.getResource().isEmpty()) {
        resource_ = other.resource_;
        onChanged();
      }
      if (!other.getStatus().isEmpty()) {
        status_ = other.status_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @Override
    public final boolean isInitialized() {
      return true;
    }

    @Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      EventRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (EventRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.google.protobuf.Timestamp date_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> dateBuilder_;
    /**
     * <code>.google.protobuf.Timestamp date = 1;</code>
     * @return Whether the date field is set.
     */
    public boolean hasDate() {
      return dateBuilder_ != null || date_ != null;
    }
    /**
     * <code>.google.protobuf.Timestamp date = 1;</code>
     * @return The date.
     */
    public com.google.protobuf.Timestamp getDate() {
      if (dateBuilder_ == null) {
        return date_ == null ? com.google.protobuf.Timestamp.getDefaultInstance() : date_;
      } else {
        return dateBuilder_.getMessage();
      }
    }
    /**
     * <code>.google.protobuf.Timestamp date = 1;</code>
     */
    public Builder setDate(com.google.protobuf.Timestamp value) {
      if (dateBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        date_ = value;
        onChanged();
      } else {
        dateBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp date = 1;</code>
     */
    public Builder setDate(
        com.google.protobuf.Timestamp.Builder builderForValue) {
      if (dateBuilder_ == null) {
        date_ = builderForValue.build();
        onChanged();
      } else {
        dateBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp date = 1;</code>
     */
    public Builder mergeDate(com.google.protobuf.Timestamp value) {
      if (dateBuilder_ == null) {
        if (date_ != null) {
          date_ =
            com.google.protobuf.Timestamp.newBuilder(date_).mergeFrom(value).buildPartial();
        } else {
          date_ = value;
        }
        onChanged();
      } else {
        dateBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp date = 1;</code>
     */
    public Builder clearDate() {
      if (dateBuilder_ == null) {
        date_ = null;
        onChanged();
      } else {
        date_ = null;
        dateBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp date = 1;</code>
     */
    public com.google.protobuf.Timestamp.Builder getDateBuilder() {
      
      onChanged();
      return getDateFieldBuilder().getBuilder();
    }
    /**
     * <code>.google.protobuf.Timestamp date = 1;</code>
     */
    public com.google.protobuf.TimestampOrBuilder getDateOrBuilder() {
      if (dateBuilder_ != null) {
        return dateBuilder_.getMessageOrBuilder();
      } else {
        return date_ == null ?
            com.google.protobuf.Timestamp.getDefaultInstance() : date_;
      }
    }
    /**
     * <code>.google.protobuf.Timestamp date = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> 
        getDateFieldBuilder() {
      if (dateBuilder_ == null) {
        dateBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder>(
                getDate(),
                getParentForChildren(),
                isClean());
        date_ = null;
      }
      return dateBuilder_;
    }

    private Object microservice_ = "";
    /**
     * <code>string microservice = 2;</code>
     * @return The microservice.
     */
    public String getMicroservice() {
      Object ref = microservice_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        microservice_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string microservice = 2;</code>
     * @return The bytes for microservice.
     */
    public com.google.protobuf.ByteString
        getMicroserviceBytes() {
      Object ref = microservice_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        microservice_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string microservice = 2;</code>
     * @param value The microservice to set.
     * @return This builder for chaining.
     */
    public Builder setMicroservice(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      microservice_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string microservice = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearMicroservice() {
      
      microservice_ = getDefaultInstance().getMicroservice();
      onChanged();
      return this;
    }
    /**
     * <code>string microservice = 2;</code>
     * @param value The bytes for microservice to set.
     * @return This builder for chaining.
     */
    public Builder setMicroserviceBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      microservice_ = value;
      onChanged();
      return this;
    }

    private Object user_ = "";
    /**
     * <code>string user = 3;</code>
     * @return The user.
     */
    public String getUser() {
      Object ref = user_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        user_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string user = 3;</code>
     * @return The bytes for user.
     */
    public com.google.protobuf.ByteString
        getUserBytes() {
      Object ref = user_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        user_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string user = 3;</code>
     * @param value The user to set.
     * @return This builder for chaining.
     */
    public Builder setUser(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      user_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string user = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearUser() {
      
      user_ = getDefaultInstance().getUser();
      onChanged();
      return this;
    }
    /**
     * <code>string user = 3;</code>
     * @param value The bytes for user to set.
     * @return This builder for chaining.
     */
    public Builder setUserBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      user_ = value;
      onChanged();
      return this;
    }

    private int action_ = 0;
    /**
     * <code>.EventRequest.actionType action = 4;</code>
     * @return The enum numeric value on the wire for action.
     */
    @Override public int getActionValue() {
      return action_;
    }
    /**
     * <code>.EventRequest.actionType action = 4;</code>
     * @param value The enum numeric value on the wire for action to set.
     * @return This builder for chaining.
     */
    public Builder setActionValue(int value) {
      
      action_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.EventRequest.actionType action = 4;</code>
     * @return The action.
     */
    @Override
    public EventRequest.actionType getAction() {
      @SuppressWarnings("deprecation")
      EventRequest.actionType result = EventRequest.actionType.valueOf(action_);
      return result == null ? EventRequest.actionType.UNRECOGNIZED : result;
    }
    /**
     * <code>.EventRequest.actionType action = 4;</code>
     * @param value The action to set.
     * @return This builder for chaining.
     */
    public Builder setAction(EventRequest.actionType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      action_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.EventRequest.actionType action = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearAction() {
      
      action_ = 0;
      onChanged();
      return this;
    }

    private Object resource_ = "";
    /**
     * <code>string resource = 5;</code>
     * @return The resource.
     */
    public String getResource() {
      Object ref = resource_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        resource_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string resource = 5;</code>
     * @return The bytes for resource.
     */
    public com.google.protobuf.ByteString
        getResourceBytes() {
      Object ref = resource_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        resource_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string resource = 5;</code>
     * @param value The resource to set.
     * @return This builder for chaining.
     */
    public Builder setResource(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      resource_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string resource = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearResource() {
      
      resource_ = getDefaultInstance().getResource();
      onChanged();
      return this;
    }
    /**
     * <code>string resource = 5;</code>
     * @param value The bytes for resource to set.
     * @return This builder for chaining.
     */
    public Builder setResourceBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      resource_ = value;
      onChanged();
      return this;
    }

    private Object status_ = "";
    /**
     * <code>string status = 6;</code>
     * @return The status.
     */
    public String getStatus() {
      Object ref = status_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        status_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string status = 6;</code>
     * @return The bytes for status.
     */
    public com.google.protobuf.ByteString
        getStatusBytes() {
      Object ref = status_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        status_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string status = 6;</code>
     * @param value The status to set.
     * @return This builder for chaining.
     */
    public Builder setStatus(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      status_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string status = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearStatus() {
      
      status_ = getDefaultInstance().getStatus();
      onChanged();
      return this;
    }
    /**
     * <code>string status = 6;</code>
     * @param value The bytes for status to set.
     * @return This builder for chaining.
     */
    public Builder setStatusBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      status_ = value;
      onChanged();
      return this;
    }
    @Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:EventRequest)
  }

  // @@protoc_insertion_point(class_scope:EventRequest)
  private static final EventRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new EventRequest();
  }

  public static EventRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<EventRequest>
      PARSER = new com.google.protobuf.AbstractParser<EventRequest>() {
    @Override
    public EventRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new EventRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<EventRequest> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<EventRequest> getParserForType() {
    return PARSER;
  }

  @Override
  public EventRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

