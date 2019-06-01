import getMuiTheme from 'material-ui/styles/getMuiTheme';
import {green500, green900, red500} from 'material-ui/styles/colors';

const customTheme = {
  palette: {
    primary1Color: green500,
    primary2Color: green500,
    primary3Color: green500,
    accent1Color: red500,
    accent3Color: green900,
  }
};

const theme = getMuiTheme(customTheme);

export default theme;