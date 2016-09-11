precision mediump float;
uniform float uf_alpha;
uniform vec2 uv2_ballPosition;
varying vec2 vv2_texCoord;
varying vec4 vv4_Colors;
uniform sampler2D us_texture;
void main() {
    vec4 color = texture2D( us_texture, vv2_texCoord);
//       if (color.a < 0.01) discard;+sssss
//       float distancePoint = distance(gl_FragCoord.xy, uv2_ballPosition.xy);+
//       if (distancePoint < 50.0){+
//       vec2 randomSeed = vec2(gl_FragCoord.x/uv2_ballPosition.x, gl_FragCoord.y/uv2_ballPosition.y);+
//       float invertedValue = fract(sin(dot(randomSeed.xy,vec2(12.9898,78.233))) * 43758.5453);+
//       vec4 color2 = vec4((color.r + vv4_Colors.r)*invertedValue, color.g + vv4_Colors.g, color.b + vv4_Colors.b, uf_alpha * 0.8);+
//       gl_FragColor = vec4(color2.r, color2.g, color2.b, color2.a);+
//       } else {+
//       }+
    gl_FragColor = vec4(color.r + vv4_Colors.r, color.g + vv4_Colors.g, color.b + vv4_Colors.b, uf_alpha);
}
