{ pkgs ? import <nixpkgs> {} }:

pkgs.mkShell {
  buildInputs = [
    pkgs.jdk11
  ];

  shellHook = ''
    export JAVA_HOME=${pkgs.jdk11}
  '';
}
