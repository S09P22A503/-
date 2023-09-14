from locust import HttpUser, task, between
import random
class WebsiteUser(HttpUser):
    
    # 요청 간 대기 시간을 설정합니다. 이 경우, 거의 대기 시간 없이 연속적으로 요청합니다.
    wait_time = between(2, 5)  # 이렇게 설정하면 대략 초당 100회의 요청이 수행됩니다.

    @task
    def post_data(self):
        dtype_list = ["viewcount","buycount","viewingtime"]
        user_id = random.randint(1,1000)
        article_id = random.randint(1,1000)
        dtype = dtype_list[random.randint(0,2)]
        created_data = {
            "user_id":user_id,
            "article_id":article_id,
            "dtype":dtype 
        }
        if(dtype=="viewingtime"):
           created_data["viewingtime"] = round(random.uniform(0,60),2)
        
        self.client.post("/data", json=created_data)
