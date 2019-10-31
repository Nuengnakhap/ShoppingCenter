import React from "react";
import "../../assets/css/header.css";
import PropTypes from "prop-types";

function Header(props) {
  return (
    <div className={props.mainClass}>
      {props.componentLeft}
      <div>
        <img
          src={props.logo}
          className="center-images"
          style={{ padding: "0 5vmin" }}
          alt="logo"
        />
      </div>
    </div>
  );
}

export default Header;

Header.propTypes = {
  logo: PropTypes.string,
  componentLeft: PropTypes.element,
  mainClass: PropTypes.string
};
