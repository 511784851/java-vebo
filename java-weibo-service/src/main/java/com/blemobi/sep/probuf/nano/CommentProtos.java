// Generated by the protocol buffer compiler.  DO NOT EDIT!

package com.blemobi.sep.probuf.nano;

@SuppressWarnings("hiding")
public interface CommentProtos {

  public static final class PComment extends
      com.google.protobuf.nano.MessageNano {

    private static volatile PComment[] _emptyArray;
    public static PComment[] emptyArray() {
      // Lazily initializes the empty array
      if (_emptyArray == null) {
        synchronized (
            com.google.protobuf.nano.InternalNano.LAZY_INIT_LOCK) {
          if (_emptyArray == null) {
            _emptyArray = new PComment[0];
          }
        }
      }
      return _emptyArray;
    }

    // optional string id = 1;
    public java.lang.String id;

    // optional string uuid = 2;
    public java.lang.String uuid;

    // optional string nickname = 3;
    public java.lang.String nickname;

    // optional string headImgUrl = 4;
    public java.lang.String headImgUrl;

    // optional string atUuid = 5;
    public java.lang.String atUuid;

    // optional string atNickname = 6;
    public java.lang.String atNickname;

    // optional bool isShield = 8;
    public boolean isShield;

    // optional int32 vote = 9;
    public int vote;

    // optional int32 children = 10;
    public int children;

    // optional int32 upvotes = 11;
    public int upvotes;

    // optional int32 downvotes = 12;
    public int downvotes;

    // optional int64 createTime = 13;
    public long createTime;

    // optional string text = 14;
    public java.lang.String text;

    // optional .common.PAudio audio = 15;
    public com.blemobi.sep.probuf.nano.NewsProtos.PAudio audio;

    public PComment() {
      clear();
    }

    public PComment clear() {
      id = "";
      uuid = "";
      nickname = "";
      headImgUrl = "";
      atUuid = "";
      atNickname = "";
      isShield = false;
      vote = 0;
      children = 0;
      upvotes = 0;
      downvotes = 0;
      createTime = 0L;
      text = "";
      audio = null;
      cachedSize = -1;
      return this;
    }

    @Override
    public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano output)
        throws java.io.IOException {
      if (!this.id.equals("")) {
        output.writeString(1, this.id);
      }
      if (!this.uuid.equals("")) {
        output.writeString(2, this.uuid);
      }
      if (!this.nickname.equals("")) {
        output.writeString(3, this.nickname);
      }
      if (!this.headImgUrl.equals("")) {
        output.writeString(4, this.headImgUrl);
      }
      if (!this.atUuid.equals("")) {
        output.writeString(5, this.atUuid);
      }
      if (!this.atNickname.equals("")) {
        output.writeString(6, this.atNickname);
      }
      if (this.isShield != false) {
        output.writeBool(8, this.isShield);
      }
      if (this.vote != 0) {
        output.writeInt32(9, this.vote);
      }
      if (this.children != 0) {
        output.writeInt32(10, this.children);
      }
      if (this.upvotes != 0) {
        output.writeInt32(11, this.upvotes);
      }
      if (this.downvotes != 0) {
        output.writeInt32(12, this.downvotes);
      }
      if (this.createTime != 0L) {
        output.writeInt64(13, this.createTime);
      }
      if (!this.text.equals("")) {
        output.writeString(14, this.text);
      }
      if (this.audio != null) {
        output.writeMessage(15, this.audio);
      }
      super.writeTo(output);
    }

    @Override
    protected int computeSerializedSize() {
      int size = super.computeSerializedSize();
      if (!this.id.equals("")) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeStringSize(1, this.id);
      }
      if (!this.uuid.equals("")) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeStringSize(2, this.uuid);
      }
      if (!this.nickname.equals("")) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeStringSize(3, this.nickname);
      }
      if (!this.headImgUrl.equals("")) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeStringSize(4, this.headImgUrl);
      }
      if (!this.atUuid.equals("")) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeStringSize(5, this.atUuid);
      }
      if (!this.atNickname.equals("")) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeStringSize(6, this.atNickname);
      }
      if (this.isShield != false) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeBoolSize(8, this.isShield);
      }
      if (this.vote != 0) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeInt32Size(9, this.vote);
      }
      if (this.children != 0) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeInt32Size(10, this.children);
      }
      if (this.upvotes != 0) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeInt32Size(11, this.upvotes);
      }
      if (this.downvotes != 0) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeInt32Size(12, this.downvotes);
      }
      if (this.createTime != 0L) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeInt64Size(13, this.createTime);
      }
      if (!this.text.equals("")) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeStringSize(14, this.text);
      }
      if (this.audio != null) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
          .computeMessageSize(15, this.audio);
      }
      return size;
    }

    @Override
    public PComment mergeFrom(
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
          case 10: {
            this.id = input.readString();
            break;
          }
          case 18: {
            this.uuid = input.readString();
            break;
          }
          case 26: {
            this.nickname = input.readString();
            break;
          }
          case 34: {
            this.headImgUrl = input.readString();
            break;
          }
          case 42: {
            this.atUuid = input.readString();
            break;
          }
          case 50: {
            this.atNickname = input.readString();
            break;
          }
          case 64: {
            this.isShield = input.readBool();
            break;
          }
          case 72: {
            this.vote = input.readInt32();
            break;
          }
          case 80: {
            this.children = input.readInt32();
            break;
          }
          case 88: {
            this.upvotes = input.readInt32();
            break;
          }
          case 96: {
            this.downvotes = input.readInt32();
            break;
          }
          case 104: {
            this.createTime = input.readInt64();
            break;
          }
          case 114: {
            this.text = input.readString();
            break;
          }
          case 122: {
            if (this.audio == null) {
              this.audio = new com.blemobi.sep.probuf.nano.NewsProtos.PAudio();
            }
            input.readMessage(this.audio);
            break;
          }
        }
      }
    }

    public static PComment parseFrom(byte[] data)
        throws com.google.protobuf.nano.InvalidProtocolBufferNanoException {
      return com.google.protobuf.nano.MessageNano.mergeFrom(new PComment(), data);
    }

    public static PComment parseFrom(
            com.google.protobuf.nano.CodedInputByteBufferNano input)
        throws java.io.IOException {
      return new PComment().mergeFrom(input);
    }
  }

  public static final class PCommentLevel extends
      com.google.protobuf.nano.MessageNano {

    private static volatile PCommentLevel[] _emptyArray;
    public static PCommentLevel[] emptyArray() {
      // Lazily initializes the empty array
      if (_emptyArray == null) {
        synchronized (
            com.google.protobuf.nano.InternalNano.LAZY_INIT_LOCK) {
          if (_emptyArray == null) {
            _emptyArray = new PCommentLevel[0];
          }
        }
      }
      return _emptyArray;
    }

    // optional int32 level = 1;
    public int level;

    // optional int32 count = 2;
    public int count;

    // optional int32 interactions = 3;
    public int interactions;

    // repeated .common.PComment comments = 4;
    public com.blemobi.sep.probuf.nano.CommentProtos.PComment[] comments;

    public PCommentLevel() {
      clear();
    }

    public PCommentLevel clear() {
      level = 0;
      count = 0;
      interactions = 0;
      comments = com.blemobi.sep.probuf.nano.CommentProtos.PComment.emptyArray();
      cachedSize = -1;
      return this;
    }

    @Override
    public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano output)
        throws java.io.IOException {
      if (this.level != 0) {
        output.writeInt32(1, this.level);
      }
      if (this.count != 0) {
        output.writeInt32(2, this.count);
      }
      if (this.interactions != 0) {
        output.writeInt32(3, this.interactions);
      }
      if (this.comments != null && this.comments.length > 0) {
        for (int i = 0; i < this.comments.length; i++) {
          com.blemobi.sep.probuf.nano.CommentProtos.PComment element = this.comments[i];
          if (element != null) {
            output.writeMessage(4, element);
          }
        }
      }
      super.writeTo(output);
    }

    @Override
    protected int computeSerializedSize() {
      int size = super.computeSerializedSize();
      if (this.level != 0) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeInt32Size(1, this.level);
      }
      if (this.count != 0) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeInt32Size(2, this.count);
      }
      if (this.interactions != 0) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeInt32Size(3, this.interactions);
      }
      if (this.comments != null && this.comments.length > 0) {
        for (int i = 0; i < this.comments.length; i++) {
          com.blemobi.sep.probuf.nano.CommentProtos.PComment element = this.comments[i];
          if (element != null) {
            size += com.google.protobuf.nano.CodedOutputByteBufferNano
              .computeMessageSize(4, element);
          }
        }
      }
      return size;
    }

    @Override
    public PCommentLevel mergeFrom(
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
          case 8: {
            this.level = input.readInt32();
            break;
          }
          case 16: {
            this.count = input.readInt32();
            break;
          }
          case 24: {
            this.interactions = input.readInt32();
            break;
          }
          case 34: {
            int arrayLength = com.google.protobuf.nano.WireFormatNano
                .getRepeatedFieldArrayLength(input, 34);
            int i = this.comments == null ? 0 : this.comments.length;
            com.blemobi.sep.probuf.nano.CommentProtos.PComment[] newArray =
                new com.blemobi.sep.probuf.nano.CommentProtos.PComment[i + arrayLength];
            if (i != 0) {
              java.lang.System.arraycopy(this.comments, 0, newArray, 0, i);
            }
            for (; i < newArray.length - 1; i++) {
              newArray[i] = new com.blemobi.sep.probuf.nano.CommentProtos.PComment();
              input.readMessage(newArray[i]);
              input.readTag();
            }
            // Last one without readTag.
            newArray[i] = new com.blemobi.sep.probuf.nano.CommentProtos.PComment();
            input.readMessage(newArray[i]);
            this.comments = newArray;
            break;
          }
        }
      }
    }

    public static PCommentLevel parseFrom(byte[] data)
        throws com.google.protobuf.nano.InvalidProtocolBufferNanoException {
      return com.google.protobuf.nano.MessageNano.mergeFrom(new PCommentLevel(), data);
    }

    public static PCommentLevel parseFrom(
            com.google.protobuf.nano.CodedInputByteBufferNano input)
        throws java.io.IOException {
      return new PCommentLevel().mergeFrom(input);
    }
  }

  public static final class PCommentList extends
      com.google.protobuf.nano.MessageNano {

    private static volatile PCommentList[] _emptyArray;
    public static PCommentList[] emptyArray() {
      // Lazily initializes the empty array
      if (_emptyArray == null) {
        synchronized (
            com.google.protobuf.nano.InternalNano.LAZY_INIT_LOCK) {
          if (_emptyArray == null) {
            _emptyArray = new PCommentList[0];
          }
        }
      }
      return _emptyArray;
    }

    // repeated .common.PCommentLevel comments = 1;
    public com.blemobi.sep.probuf.nano.CommentProtos.PCommentLevel[] comments;

    public PCommentList() {
      clear();
    }

    public PCommentList clear() {
      comments = com.blemobi.sep.probuf.nano.CommentProtos.PCommentLevel.emptyArray();
      cachedSize = -1;
      return this;
    }

    @Override
    public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano output)
        throws java.io.IOException {
      if (this.comments != null && this.comments.length > 0) {
        for (int i = 0; i < this.comments.length; i++) {
          com.blemobi.sep.probuf.nano.CommentProtos.PCommentLevel element = this.comments[i];
          if (element != null) {
            output.writeMessage(1, element);
          }
        }
      }
      super.writeTo(output);
    }

    @Override
    protected int computeSerializedSize() {
      int size = super.computeSerializedSize();
      if (this.comments != null && this.comments.length > 0) {
        for (int i = 0; i < this.comments.length; i++) {
          com.blemobi.sep.probuf.nano.CommentProtos.PCommentLevel element = this.comments[i];
          if (element != null) {
            size += com.google.protobuf.nano.CodedOutputByteBufferNano
              .computeMessageSize(1, element);
          }
        }
      }
      return size;
    }

    @Override
    public PCommentList mergeFrom(
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
          case 10: {
            int arrayLength = com.google.protobuf.nano.WireFormatNano
                .getRepeatedFieldArrayLength(input, 10);
            int i = this.comments == null ? 0 : this.comments.length;
            com.blemobi.sep.probuf.nano.CommentProtos.PCommentLevel[] newArray =
                new com.blemobi.sep.probuf.nano.CommentProtos.PCommentLevel[i + arrayLength];
            if (i != 0) {
              java.lang.System.arraycopy(this.comments, 0, newArray, 0, i);
            }
            for (; i < newArray.length - 1; i++) {
              newArray[i] = new com.blemobi.sep.probuf.nano.CommentProtos.PCommentLevel();
              input.readMessage(newArray[i]);
              input.readTag();
            }
            // Last one without readTag.
            newArray[i] = new com.blemobi.sep.probuf.nano.CommentProtos.PCommentLevel();
            input.readMessage(newArray[i]);
            this.comments = newArray;
            break;
          }
        }
      }
    }

    public static PCommentList parseFrom(byte[] data)
        throws com.google.protobuf.nano.InvalidProtocolBufferNanoException {
      return com.google.protobuf.nano.MessageNano.mergeFrom(new PCommentList(), data);
    }

    public static PCommentList parseFrom(
            com.google.protobuf.nano.CodedInputByteBufferNano input)
        throws java.io.IOException {
      return new PCommentList().mergeFrom(input);
    }
  }

  public static final class PVoteUser extends
      com.google.protobuf.nano.MessageNano {

    private static volatile PVoteUser[] _emptyArray;
    public static PVoteUser[] emptyArray() {
      // Lazily initializes the empty array
      if (_emptyArray == null) {
        synchronized (
            com.google.protobuf.nano.InternalNano.LAZY_INIT_LOCK) {
          if (_emptyArray == null) {
            _emptyArray = new PVoteUser[0];
          }
        }
      }
      return _emptyArray;
    }

    // optional string uuid = 1;
    public java.lang.String uuid;

    // optional string nickname = 2;
    public java.lang.String nickname;

    // optional string headImgUrl = 3;
    public java.lang.String headImgUrl;

    public PVoteUser() {
      clear();
    }

    public PVoteUser clear() {
      uuid = "";
      nickname = "";
      headImgUrl = "";
      cachedSize = -1;
      return this;
    }

    @Override
    public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano output)
        throws java.io.IOException {
      if (!this.uuid.equals("")) {
        output.writeString(1, this.uuid);
      }
      if (!this.nickname.equals("")) {
        output.writeString(2, this.nickname);
      }
      if (!this.headImgUrl.equals("")) {
        output.writeString(3, this.headImgUrl);
      }
      super.writeTo(output);
    }

    @Override
    protected int computeSerializedSize() {
      int size = super.computeSerializedSize();
      if (!this.uuid.equals("")) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeStringSize(1, this.uuid);
      }
      if (!this.nickname.equals("")) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeStringSize(2, this.nickname);
      }
      if (!this.headImgUrl.equals("")) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeStringSize(3, this.headImgUrl);
      }
      return size;
    }

    @Override
    public PVoteUser mergeFrom(
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
          case 10: {
            this.uuid = input.readString();
            break;
          }
          case 18: {
            this.nickname = input.readString();
            break;
          }
          case 26: {
            this.headImgUrl = input.readString();
            break;
          }
        }
      }
    }

    public static PVoteUser parseFrom(byte[] data)
        throws com.google.protobuf.nano.InvalidProtocolBufferNanoException {
      return com.google.protobuf.nano.MessageNano.mergeFrom(new PVoteUser(), data);
    }

    public static PVoteUser parseFrom(
            com.google.protobuf.nano.CodedInputByteBufferNano input)
        throws java.io.IOException {
      return new PVoteUser().mergeFrom(input);
    }
  }

  public static final class PVoteUserList extends
      com.google.protobuf.nano.MessageNano {

    private static volatile PVoteUserList[] _emptyArray;
    public static PVoteUserList[] emptyArray() {
      // Lazily initializes the empty array
      if (_emptyArray == null) {
        synchronized (
            com.google.protobuf.nano.InternalNano.LAZY_INIT_LOCK) {
          if (_emptyArray == null) {
            _emptyArray = new PVoteUserList[0];
          }
        }
      }
      return _emptyArray;
    }

    // repeated .common.PVoteUser user = 1;
    public com.blemobi.sep.probuf.nano.CommentProtos.PVoteUser[] user;

    public PVoteUserList() {
      clear();
    }

    public PVoteUserList clear() {
      user = com.blemobi.sep.probuf.nano.CommentProtos.PVoteUser.emptyArray();
      cachedSize = -1;
      return this;
    }

    @Override
    public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano output)
        throws java.io.IOException {
      if (this.user != null && this.user.length > 0) {
        for (int i = 0; i < this.user.length; i++) {
          com.blemobi.sep.probuf.nano.CommentProtos.PVoteUser element = this.user[i];
          if (element != null) {
            output.writeMessage(1, element);
          }
        }
      }
      super.writeTo(output);
    }

    @Override
    protected int computeSerializedSize() {
      int size = super.computeSerializedSize();
      if (this.user != null && this.user.length > 0) {
        for (int i = 0; i < this.user.length; i++) {
          com.blemobi.sep.probuf.nano.CommentProtos.PVoteUser element = this.user[i];
          if (element != null) {
            size += com.google.protobuf.nano.CodedOutputByteBufferNano
              .computeMessageSize(1, element);
          }
        }
      }
      return size;
    }

    @Override
    public PVoteUserList mergeFrom(
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
          case 10: {
            int arrayLength = com.google.protobuf.nano.WireFormatNano
                .getRepeatedFieldArrayLength(input, 10);
            int i = this.user == null ? 0 : this.user.length;
            com.blemobi.sep.probuf.nano.CommentProtos.PVoteUser[] newArray =
                new com.blemobi.sep.probuf.nano.CommentProtos.PVoteUser[i + arrayLength];
            if (i != 0) {
              java.lang.System.arraycopy(this.user, 0, newArray, 0, i);
            }
            for (; i < newArray.length - 1; i++) {
              newArray[i] = new com.blemobi.sep.probuf.nano.CommentProtos.PVoteUser();
              input.readMessage(newArray[i]);
              input.readTag();
            }
            // Last one without readTag.
            newArray[i] = new com.blemobi.sep.probuf.nano.CommentProtos.PVoteUser();
            input.readMessage(newArray[i]);
            this.user = newArray;
            break;
          }
        }
      }
    }

    public static PVoteUserList parseFrom(byte[] data)
        throws com.google.protobuf.nano.InvalidProtocolBufferNanoException {
      return com.google.protobuf.nano.MessageNano.mergeFrom(new PVoteUserList(), data);
    }

    public static PVoteUserList parseFrom(
            com.google.protobuf.nano.CodedInputByteBufferNano input)
        throws java.io.IOException {
      return new PVoteUserList().mergeFrom(input);
    }
  }

  public static final class PInt32Array extends
      com.google.protobuf.nano.MessageNano {

    private static volatile PInt32Array[] _emptyArray;
    public static PInt32Array[] emptyArray() {
      // Lazily initializes the empty array
      if (_emptyArray == null) {
        synchronized (
            com.google.protobuf.nano.InternalNano.LAZY_INIT_LOCK) {
          if (_emptyArray == null) {
            _emptyArray = new PInt32Array[0];
          }
        }
      }
      return _emptyArray;
    }

    // repeated int32 array = 1;
    public int[] array;

    public PInt32Array() {
      clear();
    }

    public PInt32Array clear() {
      array = com.google.protobuf.nano.WireFormatNano.EMPTY_INT_ARRAY;
      cachedSize = -1;
      return this;
    }

    @Override
    public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano output)
        throws java.io.IOException {
      if (this.array != null && this.array.length > 0) {
        for (int i = 0; i < this.array.length; i++) {
          output.writeInt32(1, this.array[i]);
        }
      }
      super.writeTo(output);
    }

    @Override
    protected int computeSerializedSize() {
      int size = super.computeSerializedSize();
      if (this.array != null && this.array.length > 0) {
        int dataSize = 0;
        for (int i = 0; i < this.array.length; i++) {
          int element = this.array[i];
          dataSize += com.google.protobuf.nano.CodedOutputByteBufferNano
              .computeInt32SizeNoTag(element);
        }
        size += dataSize;
        size += 1 * this.array.length;
      }
      return size;
    }

    @Override
    public PInt32Array mergeFrom(
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
          case 8: {
            int arrayLength = com.google.protobuf.nano.WireFormatNano
                .getRepeatedFieldArrayLength(input, 8);
            int i = this.array == null ? 0 : this.array.length;
            int[] newArray = new int[i + arrayLength];
            if (i != 0) {
              java.lang.System.arraycopy(this.array, 0, newArray, 0, i);
            }
            for (; i < newArray.length - 1; i++) {
              newArray[i] = input.readInt32();
              input.readTag();
            }
            // Last one without readTag.
            newArray[i] = input.readInt32();
            this.array = newArray;
            break;
          }
          case 10: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            // First pass to compute array length.
            int arrayLength = 0;
            int startPos = input.getPosition();
            while (input.getBytesUntilLimit() > 0) {
              input.readInt32();
              arrayLength++;
            }
            input.rewindToPosition(startPos);
            int i = this.array == null ? 0 : this.array.length;
            int[] newArray = new int[i + arrayLength];
            if (i != 0) {
              java.lang.System.arraycopy(this.array, 0, newArray, 0, i);
            }
            for (; i < newArray.length; i++) {
              newArray[i] = input.readInt32();
            }
            this.array = newArray;
            input.popLimit(limit);
            break;
          }
        }
      }
    }

    public static PInt32Array parseFrom(byte[] data)
        throws com.google.protobuf.nano.InvalidProtocolBufferNanoException {
      return com.google.protobuf.nano.MessageNano.mergeFrom(new PInt32Array(), data);
    }

    public static PInt32Array parseFrom(
            com.google.protobuf.nano.CodedInputByteBufferNano input)
        throws java.io.IOException {
      return new PInt32Array().mergeFrom(input);
    }
  }

  public static final class PCommentBool extends
      com.google.protobuf.nano.MessageNano {

    private static volatile PCommentBool[] _emptyArray;
    public static PCommentBool[] emptyArray() {
      // Lazily initializes the empty array
      if (_emptyArray == null) {
        synchronized (
            com.google.protobuf.nano.InternalNano.LAZY_INIT_LOCK) {
          if (_emptyArray == null) {
            _emptyArray = new PCommentBool[0];
          }
        }
      }
      return _emptyArray;
    }

    // optional bool isEnable = 1;
    public boolean isEnable;

    public PCommentBool() {
      clear();
    }

    public PCommentBool clear() {
      isEnable = false;
      cachedSize = -1;
      return this;
    }

    @Override
    public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano output)
        throws java.io.IOException {
      if (this.isEnable != false) {
        output.writeBool(1, this.isEnable);
      }
      super.writeTo(output);
    }

    @Override
    protected int computeSerializedSize() {
      int size = super.computeSerializedSize();
      if (this.isEnable != false) {
        size += com.google.protobuf.nano.CodedOutputByteBufferNano
            .computeBoolSize(1, this.isEnable);
      }
      return size;
    }

    @Override
    public PCommentBool mergeFrom(
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
          case 8: {
            this.isEnable = input.readBool();
            break;
          }
        }
      }
    }

    public static PCommentBool parseFrom(byte[] data)
        throws com.google.protobuf.nano.InvalidProtocolBufferNanoException {
      return com.google.protobuf.nano.MessageNano.mergeFrom(new PCommentBool(), data);
    }

    public static PCommentBool parseFrom(
            com.google.protobuf.nano.CodedInputByteBufferNano input)
        throws java.io.IOException {
      return new PCommentBool().mergeFrom(input);
    }
  }
}