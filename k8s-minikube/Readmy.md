

## Na podstawie artykulu Medium

[Running in Minikube (Kubernetes) a Spring Boot API that uses Spring Data JPA and PostgreSQL](https://medium.com/javarevisited/running-in-minikube-kubernetes-a-spring-boot-api-that-uses-spring-data-jpa-and-postgresql-7d18a8ee202e)

```
minikube start --memory=8192 --cpus=2 --vm-driver=virtualbox
kubectl create namespace dev

kubectl -n dev apply -f k8s-minikube/postgres.yaml
kubectl -n dev apply -f k8s-minikube/spring-app-security.yaml

kubectl -n dev get pods -l app=spring-app-security

MOVIE_API_HOST_PORT="$(minikube ip):$(kubectl get services -n dev spring-app-security -o go-template='{{(index .spec.ports 0).nodePort}}')"
echo $MOVIE_API_HOST_PORT

```

* Testowanie aplikacji

```
curl -i $MOVIE_API_HOST_PORT/api/users
```