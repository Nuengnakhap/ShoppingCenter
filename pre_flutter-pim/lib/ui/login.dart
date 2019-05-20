import 'package:flutter/material.dart';
import '../models/user.dart';
import './home.dart';
import 'package:toast/toast.dart';

class LoginScreen extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return LoginScreenState();
  }
}

class LoginScreenState extends State<LoginScreen> {
  final _formkey = GlobalKey<FormState>();
  TextEditingController userid = TextEditingController();
  TextEditingController password = TextEditingController();
  UserProvider userProvider = UserProvider();
  List<User> currentUsers = List();
  @override
  void initState() {
    super.initState();
    userProvider.open('member.db').then((r) {
      print("open success");
      getUsers();
    });
  }

  void getUsers() {
    userProvider.getUsers().then((r) {
      setState(() {
        currentUsers = r;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Login"),
      ),
      body: Padding(
        padding: const EdgeInsets.all(20),
        child: Form(
          key: _formkey,
          child: ListView(
            children: <Widget>[
              Image.network(
                "https://lh3.googleusercontent.com/wGFM6fbu9gQlpFiQ0twwUHDBpTAAXBgBmpAFVH5EkLjfZYtHJqlz3r5ID_PM9g_Gtih3pXqxnMqqEivHord6dYIocnY0V0B0Uw=w1600-nu-rj-l80",
                height: 200,
              ),
              TextFormField(
                controller: userid,
                decoration: InputDecoration(
                  icon: Icon(Icons.person),
                  labelText: "User Id",
                  hintText: "User Id",
                ),
                validator: (value) {
                  if (value.isEmpty) return "Username is required";
                },
              ),
              TextFormField(
                controller: password,
                decoration: InputDecoration(
                  icon: Icon(Icons.lock),
                  labelText: "Password",
                  hintText: "Password",
                ),
                obscureText: true,
                validator: (value) {
                  if (value.isEmpty) return "Password is required";
                },
              ),
              Row(
                children: <Widget>[
                  Expanded(
                    flex: 1,
                    child: RaisedButton(
                      child: Text("sign in"),
                      onPressed: () {
                        bool flag = false;
                        if (_formkey.currentState.validate()) {
                          for (int i = 0; i < currentUsers.length; i++) {
                            if (userid.text == currentUsers[i].userid &&
                                password.text == currentUsers[i].password) {
                              flag = true;
                              Navigator.pushReplacement(
                                context,
                                MaterialPageRoute(
                                  builder: (context) =>
                                      HomeScreen(user: currentUsers[i]),
                                ),
                              );
                            }
                          }
                          if (!flag) {
                            Toast.show("Invalid user or password", context,
                                duration: Toast.LENGTH_SHORT,
                                gravity: Toast.BOTTOM);
                          }
                        }
                      },
                    ),
                  ),
                ],
              ),
              Column(
                crossAxisAlignment: CrossAxisAlignment.end,
                children: <Widget>[
                  FlatButton(
                    child: Text("Register new account"),
                    onPressed: () {
                      Navigator.pushNamed(context, '/register');
                    },
                  )
                ],
              )
            ],
          ),
        ),
      ),
    );
  }
}
