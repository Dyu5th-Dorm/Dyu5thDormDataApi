package com.github.nutt1101.models;

import com.github.nutt1101.Keys;
import lombok.Builder;

@Builder
public class LoginParameters implements Parameters {
    private String account;
    private String password;

    @Override
    public String toRequestBody() {
        return String.format(
                "%s=%s&%s=%s", Keys.BODY_LOGIN_ID.getVal(), this.account,
                Keys.BODY_LOGIN_PWD.getVal(), this.password
        );
    }
}
