import { configureStore } from '@reduxjs/toolkit';
import getQViewSlice from '../slice/getQView';
import getAnswerSlice from '../slice/getAnswer';
import getTopQListSlice from '../slice/getTopQListSlice';
import getTagsSLice from '../slice/getTags';
const store = configureStore({
  reducer: {
    getTopQList: getTopQListSlice.reducer,
    getQView: getQViewSlice.reducer,
    getAnswer: getAnswerSlice.reducer,
    getTags: getTagsSLice.reducer,
  },
});

export default store;
