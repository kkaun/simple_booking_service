<%--
  Created by IntelliJ IDEA.
  User: Кира
  Date: 24.06.2017
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>


<div class="container">
    <div class="row">
        <nav class="navbar navbar-default mainNavbar" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Booking Service</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Locales<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Locale</a></li>
                            <li><a href="#">Another locale</a></li>
                        </ul>
                    </li>
                </ul>
                <div class="col-sm-3 col-md-3">
                    <form class="navbar-form" role="search">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Search" name="q">
                            <div class="input-group-btn">
                                <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                            </div>
                        </div>
                    </form>
                </div>

                <ul class="nav navbar-nav navbar-right">
                    <li class="headBtnHolder"><button type="button" class="btn btn-default btn-md btn-block headBtn"> Register your object </button></li>
                    <li class="headBtnHolder"><button type="button" class="btn btn-default btn-md btn-block headBtn"> Register as user </button></li>
                    <li class="headBtnHolder"><button type="button" class="btn btn-default btn-md btn-block headBtn"> Log in </button></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </nav>
    </div>
</div>


</body>
</html>
