import React from "react";
import PropTypes from "prop-types";
import "../../assets/css/global.css";
import "../../assets/css/store.css";
import ProductCart from "./ProductCart";

function ProductBox(props) {
  return (
    <div className="content-inner">
      <div className="title">{props.title}</div>
      <div className="content-product">
        {!!props.products &&
          props.products.map((item, index) => (
            <ProductCart
              key={index}
              title="My Product"
              stars={4.5}
              onPress={() => props.updateCart(1)}
              style={{ width: "25vmin" }}
            />
          ))}
      </div>
    </div>
  );
}

ProductBox.propTypes = {
  title: PropTypes.string,
  products: PropTypes.array
};

export default ProductBox;
