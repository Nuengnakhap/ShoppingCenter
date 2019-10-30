import React, { Component } from "react";
import "../../assets/css/header.css";
import PropTypes from "prop-types";

export default class Header extends Component {
  render() {
    return (
      <div className={this.props.mainClass}>
        {this.props.componentLeft}
        <div>
          <img
            src={this.props.logo}
            className="center-images"
            style={{ padding: "0 5vmin" }}
            alt="logo"
          />
        </div>
      </div>
    );
  }
}

Header.propTypes = {
  logo: PropTypes.string,
  componentLeft: PropTypes.element,
  mainClass: PropTypes.string
}
