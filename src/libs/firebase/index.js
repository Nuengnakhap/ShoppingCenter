import firebase from "firebase";
import "firebase/storage";

const firebaseConfig = {
  apiKey: "AIzaSyDLuwCga5XDUhVCR31zBRLxKG14Bqmg0yE",
  authDomain: "mindful-primer-169812.firebaseapp.com",
  databaseURL: "https://mindful-primer-169812.firebaseio.com",
  projectId: "mindful-primer-169812",
  storageBucket: "mindful-primer-169812.appspot.com",
  messagingSenderId: "52727576464",
  appId: "1:52727576464:web:2b28c9d2af0b2539366515"
};

firebase.initializeApp(firebaseConfig);

const storage = firebase.storage();

export { storage, firebase as default };
