# Build
custom_build(
    # Name of the container image
    ref = 'ghcr.io/mavenlj/catalog-service:latest',
    # Command to build the container image
    command = 'mvn spring-boot:build-image -Dmaven.test.skip=true -D"spring-boot.build-image.imageName"=%EXPECTED_REF%',
    deps = ['pom.xml', 'src']
)
# Deploy
k8s_yaml(['k8s/deployment.yml', 'k8s/service.yml'])
# Manage
k8s_resource('catalog-service', port_forwards=['9001'])