import React, { Component } from "react";
import { connect } from "react-redux";
import { Actions } from "../../../store/actions";

const PrivateScreen = (WarpComponent, checkLogin = false) => {
  class Authen extends Component {
    render() {
      return <WarpComponent {...this.props} />;
    }
  }

  return connect(
    state => ({
      customer: state.customer,
      cart: state.cart
    }),
    { ...Actions }
  )(Authen);
};

export default PrivateScreen;
