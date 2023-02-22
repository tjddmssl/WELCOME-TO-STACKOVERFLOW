import styled from 'styled-components';

const FooterContainer = styled.footer`
  display: flex;
  justify-content: center;
  background-color: #232629;
  width: 100%;
`;

const FooterBackGround = styled.div`
  background-color: #232629;
  max-width: 1300px;
  display: flex;
  flex: 1 1 auto;
  color: #babfc3;
  padding: 32px 12px 12px 0px;
  @media screen and (max-width: 980px) {
    flex-wrap: wrap;
  }
  @media (min-width: 0px) and (max-width: 832px) {
    flex-direction: column;
  }
  .footer-nav__div {
    display: flex;
    flex-flow: row nowrap;
    justify-content: space-around;
    gap: 30px 40px;
    margin-right: 20px;
    margin-bottom: 30px;
    width: 70%;
    @media screen and (max-width: 980px) {
      flex-direction: column;
      justify-content: start;
      gap: 0px;
    }
    @media (min-width: 0px) and (max-width: 832px) {
      margin-left: 15px;
    }
  }
  .footer-nav__ul {
    li {
      white-space: nowrap;
    }
    @media screen and (max-width: 980px) {
      display: flex;
      flex-direction: row;
      width: 90vw;
      flex-wrap: wrap;
    }
    li {
      margin-right: 10px;
    }
  }
  .footer-col__h5 {
    color: #babfc3;
    padding: 0px;
    margin: 0px 0px 15px 0px;
    white-space: nowrap;
  }
  .footer-log__img {
    flex: 0 0 auto;
    min-width: 50px;
  }
  .footer-social-container__div {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    max-width: 20%;
    /* width: 25%; */
    @media screen and (max-width: 980px) {
      max-width: 100%;
    }
  }
  .footer-logo__div {
    margin-right: 10px;
    min-width: 55px;
  }
  .footer-col__div {
    padding: 0px 0px 24px 0px;
  }
  .footer-social__ul {
    display: flex;
    flex-wrap: nowrap;
    @media screen and (max-width: 980px) {
      margin-left: 15px;
    }
  }
  .footer-social__li {
    float: left;
    margin: 0px 5px;
  }
  .footer-social__a {
    padding: 0px;
    font-size: 11px;
  }
  .footer-division__li {
    margin-top: 20px;
    @media screen and (max-width: 980px) {
      margin: 0px;
    }
  }
  a {
    color: #8c949b;
    font-size: 13px;
    padding: 4px 0px;
  }
  a:hover {
    color: #babfc3;
  }
  p {
    font-size: 11px;
    color: #8c949b;
    @media screen and (max-width: 980px) {
      margin-left: 20px;
    }
  }
`;

function Footer() {
  return (
    <FooterContainer>
      <FooterBackGround>
        <div className="footer-logo__div">
          <img
            className="footer-log__img"
            src="/img/footer-logo.png"
            alt="footer-log"
            width="55px"
            height="60px"
          />
        </div>
        <div className="footer-nav__div">
          <div className="footer-col__div">
            <h5 className="footer-col__h5">STACK OVERFLOW</h5>
            <ul className="footer-nav__ul">
              <li>
                <a href="/questions">Questions</a>
              </li>
              <li>
                <a href="/">Help</a>
              </li>
            </ul>
          </div>
          <div className="footer-col__div">
            <h5 className="footer-col__h5">PRODUCTS</h5>
            <ul className="footer-nav__ul">
              <li>
                <a href="/">Teams</a>
              </li>
              <li>
                <a href="/">Advertising</a>
              </li>
              <li>
                <a href="/">Collectives</a>
              </li>
              <li>
                <a href="/">Talent</a>
              </li>
            </ul>
          </div>
          <div className="footer-col__div">
            <h5 className="footer-col__h5">COMPANY</h5>
            <ul className="footer-nav__ul">
              <li>
                <a href="/">About</a>
              </li>
              <li>
                <a href="/">Press</a>
              </li>
              <li>
                <a href="/">Work Here</a>
              </li>
              <li>
                <a href="/">Legal</a>
              </li>
              <li>
                <a href="/">Privacy Policy</a>
              </li>
              <li>
                <a href="/">Terms of Services</a>
              </li>
              <li>
                <a href="/">Contact Us</a>
              </li>
              <li>
                <a href="/">Cookie Settings</a>
              </li>
              <li>
                <a href="/">Cookie Policy</a>
              </li>
            </ul>
          </div>
          <div className="footer-col__div">
            <h5 className="footer-col__h5">STACK EXCHANGE NETWORK</h5>
            <ul className="footer-nav__ul">
              <li>
                <a href="/">Technology</a>
              </li>
              <li>
                <a href="/">Culture & recreation</a>
              </li>
              <li>
                <a href="/">Life & arts</a>
              </li>
              <li>
                <a href="/">Professional</a>
              </li>
              <li>
                <a href="/">Business</a>
              </li>
              <li className="footer-division__li">
                <a href="/">API</a>
              </li>
              <li>
                <a href="/">Data</a>
              </li>
            </ul>
          </div>
        </div>
        <div className="footer-col__div footer-social-container__div">
          <ul className="footer-social__ul">
            <li className="footer-social__li">
              <a className="footer-social__a" href="/">
                Blog
              </a>
            </li>
            <li className="footer-social__li">
              <a className="footer-social__a" href="/">
                Facebook
              </a>
            </li>
            <li className="footer-social__li">
              <a className="footer-social__a" href="/">
                Twitter
              </a>
            </li>
            <li className="footer-social__li">
              <a className="footer-social__a" href="/">
                LinkedIn
              </a>
            </li>
            <li className="footer-social__li">
              <a className="footer-social__a" href="/">
                Instagram
              </a>
            </li>
          </ul>
          <p>
            Site design / logo © 2023 Stack Exchange Inc; user contributions
            licensed under CC BY-SA. rev 2023.2.17.43248
          </p>
        </div>
      </FooterBackGround>
    </FooterContainer>
  );
}

export default Footer;
