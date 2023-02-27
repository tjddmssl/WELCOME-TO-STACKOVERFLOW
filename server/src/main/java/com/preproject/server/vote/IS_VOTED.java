package com.preproject.server.vote;

import lombok.Getter;

public enum IS_VOTED {

  NOT_SIGNED_IN("not signed in"),
  NOT_VOTED("didn't vote"),
  VOTE_PLUS("voted plus"),
  VOTE_MINUS("voted minus")
  ;

  @Getter
  private final String status;

  IS_VOTED(String status) {
    this.status = status;
  }
}
