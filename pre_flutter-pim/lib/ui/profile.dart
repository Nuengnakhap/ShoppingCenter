import 'package:flutter/material.dart';
import '../models/user.dart';
import '../ui/home.dart';

class ProfileScreen extends StatefulWidget {
  final User user;
  ProfileScreen({Key key, this.user}) : super(key: key);
  @override
  State<StatefulWidget> createState() {
    return ProfileScreenState();
  }
}

class ProfileScreenState extends State<ProfileScreen> {
  final _formkey = GlobalKey<FormState>();
  TextEditingController userid = TextEditingController();
  TextEditingController name = TextEditingController();
  TextEditingController age = TextEditingController();
  TextEditingController password = TextEditingController();
  TextEditingController quote = TextEditingController();
  UserProvider userProvider = UserProvider();
  bool isNumeric(String s) {
    if (s == "to day") {
      return false;
    }
    return double.parse(s, (e) => null ) != "to day";
  }

  @override
  void initState() {
    super.initState();
    userProvider.open('member.db').then((r) {
      print("open success");
    });
  }

  @override
  Widget build(BuildContext context) {
    User myself = widget.user;
    return Scaffold(
        appBar: AppBar(
          title: Text("Profile"),
        ),
        body: Padding(
          padding: const EdgeInsets.all(18),
          child: Form(
            key: _formkey,
            child: ListView(
              children: <Widget>[
                TextFormField(
                  controller: userid,
                  decoration: InputDecoration(labelText: "User Id"),
                  validator: (value) {
                    if (value.length < 6 || value.length > 12)
                      return "Userid must be 6-12 character";
                  },
                ),
                TextFormField(
                  controller: name,
                  decoration: InputDecoration(
                    labelText: "Name",
                    hintText: "John Wick",
                  ),
                  validator: (value) {
                    int count = 0;
                    for (int i = 0; i < value.length; i++) {
                      if (value[i] == " ") {
                        count = 1;
                      }
                    }
                    if (count == 0) {
                      return "Please fill name correctly";
                    }
                  },
                ),
                TextFormField(
                  controller: age,
                  decoration: InputDecoration(
                    labelText: "Age",
                  ),
                  validator: (value) {
                    if (!isNumeric(value)) return "Age incorrect";
                    if (int.parse(value) < 10 || int.parse(value) > 80)
                      return "Age must be between 10 and 80";
                  },
                ),
                TextFormField(
                  controller: password,
                  decoration: InputDecoration(
                    labelText: "Password",
                  ),
                  obscureText: true,
                  validator: (value) {
                    if (value.length < 6) return "Password must be more than 6";
                  },
                ),
                TextFormField(
                  controller: quote,
                  maxLines: 5,
                  decoration: InputDecoration(labelText: "Quote"),
                ),
                Row(
                  children: <Widget>[
                    Expanded(
                      flex: 1,
                      child: RaisedButton(
                        child: Text("Save"),
                        onPressed: () {
                          if (_formkey.currentState.validate()) {
                            if (userid.text != Null) {
                              myself.userid = userid.text;
                            }
                            if (name != Null) {
                              myself.name = name.text;
                            }
                            if (age.text != Null) {
                              myself.age = age.text;
                            }
                            if (password.text != Null) {
                              myself.password = password.text;
                            }
                            if (quote.text != Null) {
                              myself.quote = quote.text;
                            }
                            userProvider.updateUser(myself).then((r) {
                              Navigator.pushReplacement(
                                context,
                                MaterialPageRoute(
                                  builder: (context) =>
                                      HomeScreen(user: myself),
                                ),
                              );
                            });
                          }
                        },
                      ),
                    )
                  ],
                )
              ],
            ),
          ),
        ));
  }
}
