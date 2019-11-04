import React, { Component } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import HomeScreen from "./screen/home";
import NavBar from "./components/nav";

// ------ CSS ------ //
// import "./App.css";
import "./assets/css/fonts.css";
import "./assets/css/global.css";
import IndexScreen from "./screen/store";
import PrivateScreen from "./components/hoc/authentication/PrivateScreen";
import ProductNavigator from "./screen/product";

export default class App extends Component {
  render() {
    return (
      <Router>
        <div style={styles.app} className="background">
          <NavBar style={styles.nav} />
          {/* A <Switch> looks through its children <Route>s and
              renders the first one that matches the current URL. */}
          <Switch>
            <Route path="/home" component={PrivateScreen(IndexScreen)} />
            <Route path="/product" component={PrivateScreen(ProductNavigator)} />
            <Route path="/users">
              <HomeScreen />
            </Route>
            <Route path="/">
              <HomeScreen />
            </Route>
          </Switch>
        </div>
      </Router>
    );
  }
}

const styles = {
  app: {
    fontFamily: "Oxygen-Regular"
  },
  nav: {
    fontFamily: "Oxygen-Bold",
    height: "10vmin"
  }
};
