import { createGlobalStyle } from 'styled-components';

const GlobalStyle = createGlobalStyle`
*{
  box-sizing: border-box;
}

html,body{
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}

ul,ol,li{
  list-style-type: none;
  padding:0;
  margin: 0;
}

a{
  text-decoration: none;
}

a:hover, a:active, a:visited{
  text-decoration: none;
}

button {
  cursor: pointer;
}
`;
export default GlobalStyle;
