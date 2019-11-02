import React from "react";
import { useParams } from "react-router-dom";

function DetailScreen() {
  let { productId } = useParams();
  return <div>Detail {productId}</div>;
}

export default DetailScreen;
