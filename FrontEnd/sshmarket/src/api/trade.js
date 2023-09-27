import instance from './instance';
import processApiResponse from '../utils/api';

const getTradeList = async ({ responseFunc, data }) => {
    const { memberId, status } = data;

    try {
        const response = await instance.get(`/trades/${memberId}?status=${status}`);
        console.log(response);
        processApiResponse({ responseFunc, response });
        return response;
    } catch (e) {
        return e.response;
    }
};

const getTradeHistory = async ({ responseFunc, data }) => {
    const { memberId } = data;

    try {
        const response = await instance.get(`/trades/history/${memberId}`);
        // console.log(response);
        processApiResponse({ responseFunc, response });
        return response;
    } catch (e) {
        return e.response;
    }
};

const getUserProfile = async ({ responseFunc, data, routeTo }) => {
    const { userId } = data;
    try {
        const response = await instance.get(`/api/user/${userId}`);
        processApiResponse({ responseFunc, response });
        return response;
    } catch (e) {
        routeTo('/error');
        return e.response;
    }
};

const getLoginIdIsDuplicated = async ({ responseFunc, data, routeTo }) => {
    const { userLoginId } = data;
    try {
        const response = await instance.get(`/api/user/loginId/${userLoginId}`);
        processApiResponse({ responseFunc, response });
        return response;
    } catch (e) {
        routeTo('/error');
        return e.response;
    }
};

const getNicknameIsDuplicated = async ({ responseFunc, data, routeTo }) => {
    const { nickname } = data;
    try {
        const response = await instance.get(`/api/user/nickname/${nickname}`);
        processApiResponse({ responseFunc, response });
        return response;
    } catch (e) {
        routeTo('/error');
        return e.response;
    }
};

const getEmailIsDuplicated = async ({ responseFunc, data, routeTo }) => {
    const { email } = data;
    try {
        const response = await instance.get(`/api/user/email/${email}`);
        processApiResponse({ responseFunc, response });
        return response;
    } catch (e) {
        routeTo('/error');
        return e.response;
    }
};

const postSignUp = async ({ responseFunc, data, routeTo }) => {
    try {
        const response = await instance.post(`/api/user/signup`, data);
        processApiResponse({ responseFunc, response });
        return response;
    } catch (e) {
        routeTo('/error');
        return e.response;
    }
};

const updateUserNickname = async ({ responseFunc, data, routeTo }) => {
    try {
        const response = await instance.patch(`/api/user/nickname`, data);
        processApiResponse({ responseFunc, response });
        return response;
    } catch (e) {
        routeTo('/error');
        return e.response;
    }
};

const updateUserBio = async ({ responseFunc, data, routeTo }) => {
    try {
        const response = await instance.patch(`/api/user/bio`, data);
        processApiResponse({ responseFunc, response });
        return response;
    } catch (e) {
        routeTo('/error');
        return e.response;
    }
};

const updateUserPassword = async ({ responseFunc, data, routeTo }) => {
    try {
        const response = await instance.patch(`/api/user/password`, data);
        processApiResponse({ responseFunc, response });
        return response;
    } catch (e) {
        routeTo('/error');
        return e.response;
    }
};

const updateUserImage = async ({ responseFunc, data, routeTo }) => {
    const { userId, files } = data;
    try {
        const response = await instance.patch(`/api/user/updateImage`, files, {
            headers: {
                'Content-Type': 'multipart/form-data',
            },
            params: { userId },
        });
        processApiResponse({ responseFunc, response });
        return response;
    } catch (e) {
        routeTo('/error');
        return e.response;
    }
};

const deleteUserImage = async ({ responseFunc, data, routeTo }) => {
    const { userId } = data;
    try {
        const response = await instance.delete(`/api/user/deleteImage`, {
            params: { userId },
        });
        processApiResponse({ responseFunc, response });
        return response;
    } catch (e) {
        routeTo('/error');
        return e.response;
    }
};

const getDailyGrid = async ({ responseFunc, data, routeTo }) => {
    const { userId } = data;
    try {
        const response = await instance.get(`/api/user/dailyGrid/${userId}`);
        processApiResponse({ responseFunc, response });
        return response;
    } catch (e) {
        routeTo('/error');
        return e.response;
    }
};

const deleteUser = async ({ responseFunc, data, routeTo }) => {
    const { userId } = data;
    try {
        const response = await instance.delete(`/api/user/${userId}`);
        processApiResponse({ responseFunc, response });
        return response;
    } catch (e) {
        routeTo('/error');
        return e.response;
    }
};

const updateLanguage = async ({ responseFunc, data, routeTo }) => {
    try {
        const response = await instance.post(`/api/user/language`, data);
        processApiResponse({ responseFunc, response });
        return response;
    } catch (e) {
        routeTo('/error');
        return e.response;
    }
};

export {
    getTradeList,
    getTradeHistory,
    getUserProfile,
    getLoginIdIsDuplicated,
    getNicknameIsDuplicated,
    getEmailIsDuplicated,
    postSignUp,
    updateUserNickname,
    updateUserBio,
    updateUserImage,
    deleteUserImage,
    getDailyGrid,
    deleteUser,
    updateUserPassword,
    updateLanguage,
};
