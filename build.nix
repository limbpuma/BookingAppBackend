{ pkgs ? import <nixpkgs> {} }:

pkgs.stdenv.mkDerivation rec {
  name = "my-spring-boot-app";
  buildInputs = [ pkgs.maven ];

  src = pkgs.lib.cleanSource ./.;

  buildPhase = ''
    mvn package
  '';

  installPhase = ''
    mkdir -p $out/
    cp target/my-spring-boot-app-0.0.1-SNAPSHOT.jar $out/
  '';

  meta = with pkgs.stdenv.lib; {
    homepage = "http://example.com/my-spring-boot-app";
    description = "My awesome Spring Boot application";
    license = licenses.mit;
  };
}
