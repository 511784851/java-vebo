// Generated by the protocol buffer compiler.  DO NOT EDIT!

package com.blemobi.sep.probuf.nano;

@SuppressWarnings("hiding")
public interface LoginProtos {

  public static final class PLogin extends
      com.google.protobuf.nano.MessageNano {

    private static volatile PLogin[] _emptyArray;
    public static PLogin[] emptyArray() {
      // Lazily initializes the empty array
      if (_emptyArray == null) {
        synchronized (
            com.google.protobuf.nano.InternalNano.LAZY_INIT_LOCK) {
          if (_emptyArray == null) {
            _emptyArray = new PLogin[0];
          }
        }
      }
      return _emptyArray;
    }

    // optional bool newUser = 5;
    public boolean newUser;

    // optional string uuid = 6;
    public java.lang.String uuid;

    // optional string token = 7;
    public java.lang.String token;

    // optional string nickname = 8;
    public java.lang.String nickname;

    // optional string imgURL = 9;
    public java.lang.String imgURL;

    // optional .common.PInt32List loginTypes = 11;
    public com.blemobi.sep.probuf.nano.ResultProtos.PInt32List loginTypes;

    // optional .common.PLevelInfo levelInfo = 12;
    public com.blemobi.sep.probuf.nano.AccountProtos.PLevelInfo levelInfo;

    public PLogin() {
      clear();
    }

    public PLogin clear() {
      newUser = false;
      uuid = "";
      token = "";
      nickname = "";
      imgURL = "";
      loginTypes = null;
      levelInfo = null;
      cachedSize = -1;
      return this;
    }

    @Override
    public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano output)
        throws java.io.IOException {
      if (this.newUser != false) {
        output.writeBool(5, this.newUser);
      }
      if (!this.uuid.equals("")) {
        output.writeString(6, this.uuid);
      }
      if (!this.token.equals("")) {
        output.writeString(7, this.token);
      }
      if (!this.nickname.equals("")) {
        output.writeString(8, this.nickname);
      }
      if (!this.imgURL.equals("")) {
        output.writeString(9, this.imgURL);
      }
      if (this.loginTypes != null) {
        output.writeMessage(11, this.loginTypes);
      }
      if (this.levelInfo != null) {
        output.writeMessage(12, this.levelInfo);
      }
      super.writeTo(output);
    }

    @Override
    protected int computeSerializedSize() {
      int size = super.computeSerializedSize();
      if (this.newUser != false) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeBoolSize(5, this.newUser);
      }
      if (!this.uuid.equals("")) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeStringSize(6, this.uuid);
      }
      if (!this.token.equals("")) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeStringSize(7, this.token);
      }
      if (!this.nickname.equals("")) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeStringSize(8, this.nickname);
      }
      if (!this.imgURL.equals("")) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeStringSize(9, this.imgURL);
      }
      if (this.loginTypes != null) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
          .computeMessageSize(11, this.loginTypes);
      }
      if (this.levelInfo != null) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
          .computeMessageSize(12, this.levelInfo);
      }
      return size;
    }

    @Override
    public PLogin mergeFrom(
            com.google.protobuf.nano.CodedInputByteBufferNano input)
        throws java.io.IOException {
      while (true) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            return this;
          default: {
            if (!com.google.protobuf.nano.WireFormatNano.parseUnknownField(input, tag)) {
              return this;
            }
            break;
          }
          case 40: {
            this.newUser = input.readBool();
            break;
          }
          case 50: {
            this.uuid = input.readString();
            break;
          }
          case 58: {
            this.token = input.readString();
            break;
          }
          case 66: {
            this.nickname = input.readString();
            break;
          }
          case 74: {
            this.imgURL = input.readString();
            break;
          }
          case 90: {
            if (this.loginTypes == null) {
              this.loginTypes = new com.blemobi.sep.probuf.nano.ResultProtos.PInt32List();
            }
            input.readMessage(this.loginTypes);
            break;
          }
          case 98: {
            if (this.levelInfo == null) {
              this.levelInfo = new com.blemobi.sep.probuf.nano.AccountProtos.PLevelInfo();
            }
            input.readMessage(this.levelInfo);
            break;
          }
        }
      }
    }

    public static PLogin parseFrom(byte[] data)
        throws com.google.protobuf.nano.InvalidProtocolBufferNanoException {
      return com.google.protobuf.nano.MessageNano.mergeFrom(new PLogin(), data);
    }

    public static PLogin parseFrom(
            com.google.protobuf.nano.CodedInputByteBufferNano input)
        throws java.io.IOException {
      return new PLogin().mergeFrom(input);
    }
  }
}
