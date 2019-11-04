import React, { Component } from "react";
import { connect } from "react-redux";
import { Actions } from "../../../store/actions";
import Cart from "../../cart";

const PrivateScreen = (WarpComponent, checkLogin = false) => {
  
  class Authen extends Component {
    constructor(props) {
      super(props);
      this.state = {
        cart: props.cart.cart
      }
    }

    updateCart = (data) => {
      this.props.setCart(data);
      let { cart } = this.state;
      this.setState({ cart: cart })
    }

    render() {
      return (
        <div>
          <Cart cart={this.props.cart.cart} />
          <WarpComponent {...this.props} updateCart={this.updateCart} />;
        </div>
      )
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
