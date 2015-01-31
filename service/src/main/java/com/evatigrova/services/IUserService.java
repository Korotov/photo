package com.evatigrova.services;

import com.evatigrova.beans.User;

public interface IUserService  {

    public User loadUserByName(String name);
}
