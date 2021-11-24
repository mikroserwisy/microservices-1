Instalacja metallb
```
kubectl apply -f https://raw.githubusercontent.com/metallb/metallb/v0.10.3/manifests/namespace.yaml
kubectl apply -f https://raw.githubusercontent.com/metallb/metallb/v0.10.3/manifests/metallb.yaml
```
Instalacja ingress
```
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.0.4/deploy/static/provider/baremetal/deploy.yaml
kubectl get pods -n ingress-nginx -l app.kubernetes.io/name=ingress-nginx --watch
```
Instalacja glusterfs: hosty gfs1, gfs2
- ustawić nazwy hostów i adresy ip
- zaktualizować /etc/hosts na wszystkich maszynach
- zainstalować glusterfs
```
sudo apt update
```
```
sudo apt install glusterfs-server
```
```
sudo systemctl start glusterd
sudo systemctl enable glusterd
sudo service glusterd status
```
```
sudo mkdir -p /data/gv0
```
```
sudo gluster peer probe gfs2
```
```
sudo gluster volume create gv0 replica 2 gfs1:/data/gv0 gfs2:/data/gv0 force
```
```
sudo gluster volume start gv0
```
```
sudo gluster volume info
```
- na mszynach w klastrze zainstalować klienta glusterfs
```
sudo apt install glusterfs-client
```















