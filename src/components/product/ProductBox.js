import React from "react";
import PropTypes from "prop-types";
import "../../assets/css/global.css";
import "../../assets/css/store.css";
import ProductCart from "./ProductCart";
import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { fas } from "@fortawesome/free-solid-svg-icons";
import { fab } from "@fortawesome/free-brands-svg-icons";
import { far } from "@fortawesome/free-regular-svg-icons";

library.add(fas, fab, far);

function ProductBox(props) {
  return (
    <div className="content-inner" style={{ marginBottom: "-25px" }}>
      {!!props.title && <div className="title">{props.title}</div>}
      <div className="content-search">
        <form action="">
          <input type="search" />
          <FontAwesomeIcon
            className="search"
            icon={["fas", "search"]}
            color="black"
            style={{ margin: "0 2px", fontSize: "2vmin" }}
          />
        </form>
      </div>
      <div className="content-product">
        {!!props.products &&
          props.products.map((item, index) => (
            <ProductCart
              key={index}
              title="My Product"
              stars={4.5}
              onPress={() => props.updateCart(1)}
              // style={{ width: "33%" }}
            />
          ))}
      </div>
    </div>
  );
}

ProductBox.propTypes = {
  title: PropTypes.string,
  searchBox: PropTypes.bool,
  products: PropTypes.array
};

export default ProductBox;
