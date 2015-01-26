package com.evatigrova.service;

import com.evatigrova.beans.User;

public interface IUserService  {

    public User loadUserByName(String name);
}
