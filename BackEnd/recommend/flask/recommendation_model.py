from sparksession import CommonSparkSession

class RecommendationModel:
    __instance = None
    __model = None
    __recommendations = None
    __nuser = 100000

    def __new__(cls, *args, **kwargs):
        if not cls.__instance:
            cls.__instance = super(RecommendationModel, cls).__new__(cls, *args, **kwargs)
        return cls.__instance

    def set_recommendation_model(self, new_model):
        self.__model = new_model
        
        userSubset = CommonSparkSession() \
                                    .get_spark_session() \
                                    .createDataFrame([(i,) for i in range(self.__nuser)], ["user_id"])
        recommendationsForUser = self.__model.recommendForUserSubset(userSubset, 5)
        recommendations_list = [row.asDict() for row in recommendationsForUser.collect()]
        self.__recommendations = recommendations_list
        

    def get_recommendations(self):
        return self.__recommendations
    
    def get_recommendation_for_user(self,userId):
        return self.__recommendations[int(userId)-1]

    def get_recommendation_model(self):
        return self.__model