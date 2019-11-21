import React, { Component } from "react";
import "../../assets/css/global.css";
import "../../assets/css/store.css";
import Header from "../../components/header";
import logo from "../../assets/images/logo/shopping.png";
import ProductBox from "../../components/product/ProductBox";

export default class ProductScreen extends Component {
  render() {
    return (
      <div>
        <Header
          mainClass="content-inner logo sec-header"
          logo={logo}
          componentLeft={
            <div
              className="logo-title"
              style={{ padding: "0 5vmin", textAlign: "center" }}
            >
              <p className="home-title">SHOPPING</p>
              <p className="home-title">CENTER</p>
            </div>
          }
        />
        <ProductBox
          {...this.props}
          products={["", "", "", "", "", "", "", ""]}
        />
      </div>
    );
  }
}
