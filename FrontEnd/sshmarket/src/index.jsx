import React from 'react';
import ReactDOM from 'react-dom/client';
import reportWebVitals from './reportWebVitals';
import { Provider } from 'react-redux';
import { createStore } from 'redux';
import { persistStore } from 'redux-persist';
import rootReducer from './rootReducer';
import { PersistGate } from 'redux-persist/integration/react';
import ResetStyle from './styles/reset';
import GlobalVariableStyle from './styles/global';
import { RouterProvider } from 'react-router-dom';
import router from "./Router";

const store = createStore(rootReducer)
const persistor = persistStore(store)

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <Provider store={store}>
    <PersistGate loading={null} persistor={persistor}>
      <>
        <ResetStyle />
        <GlobalVariableStyle />
        <RouterProvider router={router} />
      </>
    </PersistGate>
  </Provider>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
