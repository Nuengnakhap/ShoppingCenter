import React, { Component } from "react";
import "../../assets/css/navbar.css";
import { Link } from "react-router-dom";
import logo from "../../assets/images/logo/shopping.png";
import { faHome, faStore, faSearch } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

export default class NavBar extends Component {
  constructor(props) {
    super(props);
    this.state = {
      activeItem: "home"
    };
  }

  isActive = menuItem => {
    if (this.state.activeItem === menuItem) {
      return "active";
    }
    return "";
  };

  setActive = menuItem => {
    this.setState({
      activeItem: menuItem
    });
  };

  render() {
    return (
      <nav className="nav__cont">
        <ul className="nav-side">
          <li className="nav__items">
            <FontAwesomeIcon icon={faHome} />
            <Link to="/">Home</Link>
          </li>

          <li className="nav__items">
            <FontAwesomeIcon icon={faSearch} />
            <Link to="/">Search</Link>
          </li>

          <li className="nav__items">
            <FontAwesomeIcon icon={faStore} />
            <Link to="/home">Store</Link>
          </li>
        </ul>
      </nav>
    );
  }

  notUse = () => {
    return (
      <nav
        className="navbar navbar-expand-lg navbar-dark fixed-top"
        style={this.props.style}
      >
        <a className="navbar-brand" href="/">
          <img
            src={logo}
            // className="App-logo center-images"
            style={{ height: "6vmin" }}
            alt="logo"
          />
        </a>
        <button
          className="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarTogglerDemo02"
          aria-controls="navbarTogglerDemo02"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <div className="collapse navbar-collapse" id="navbarTogglerDemo02">
          <ul className="navbar-nav mr-auto mt-2 mt-lg-0 mx-auto">
            <li
              className={`nav-item ${this.isActive("home")}`}
              onClick={() => this.setActive("home")}
            >
              <Link className="nav-link" to="/">
                Home
              </Link>
            </li>
            <li
              className={`nav-item ${this.isActive("about")}`}
              onClick={() => this.setActive("about")}
            >
              <Link className="nav-link" to="/about">
                About
              </Link>
            </li>
            <li className="nav-item">
              <a
                className="nav-link disabled"
                href="/"
                tabIndex="-1"
                aria-disabled="true"
              >
                Disabled
              </a>
            </li>
          </ul>
          <form className="form-inline my-2 my-lg-0">
            <input
              className="form-control mr-sm-2"
              type="search"
              placeholder="Search"
            />
            <button
              className="btn btn-outline-success my-2 my-sm-0"
              type="submit"
            >
              Search
            </button>
          </form>
        </div>
      </nav>
    );
  };
}
