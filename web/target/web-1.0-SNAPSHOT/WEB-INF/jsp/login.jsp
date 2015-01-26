<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, max-age=0, must-revalidate"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="Fri, 01 Jan 1990 00:00:00 GMT"/>

    <meta charset="UTF-8">
    <script src = "../../front-end/js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src = "../../front-end/js/bootstrap.min.js" type="text/javascript"></script>

    <link rel = "stylesheet" href="/front-end/style/bootstrap.min.css" />
    <link rel = "stylesheet" href="/front-end/style/login.css" />

    <title>Вход</title>
</head>

<body class="logging">
  <div class="row">
      <form class="form-horizontal" method="post" action="/admin/main.jsp">
          <div class="control-group">
              <label class="control-label" for="inputEmail">Email</label>
              <div class="controls">
                  <input type="text"  id="inputEmail" name="user_name" placeholder="Email">
              </div>
          </div>
          <div class="control-group">
              <label class="control-label" for="inputPassword">Password</label>
              <div class="controls">
                  <input type="password" name="user_pass" placeholder="Password">
              </div>
          </div>
          <div class="control-group">
              <div class="controls">
                  <button type="submit" class="btn custombutton">Sign in</button>
              </div>
          </div>
      </form>

  </div>

  </body>
</html>
