#version 120


uniform int ui_time;
uniform vec2 uv2_resolution;
/*
highp float rand(vec2 co){
    highp float a = 12.9898;
    highp float b = 78.233;
    highp float c = 43758.5453;
    highp float dt= dot(co.xy ,vec2(a,b));
    highp float sn= mod(dt,3.14);
    return fract(sin(sn) * c);
}
*/
void main()
{
/*

    // inputs
    vec2 resolution = vec2(uv2_resolution.x, uv2_resolution.y);
	const float height = 1.0;
    float y = 0.0;

    // configurações
    const float densityY = 30.0;
    float amplitudeY = 0.06;
    float grossuraY = 80.0;
    float frequenciaCentral = 2.0;

    // preparação das variáveis
    vec2 relativePosition = gl_fragCoord.xy/resolution.xy;
    const int quantityOfWaves = int(floor(height*densityY));



    float alpha = 0.0;

    for (int i = 0; i < quantityOfWaves; i++){

        float fi = float(i);

        // velocidade do vento

        float modi = mod(fi, 6.0);

        // variação da frequencia +/- 1.0, gerado aleatoriamente de acordo com a variável i
        float frequencia = frequenciaCentral + 0.8*cos(rand(vec2(float(i), float(i)))*360.0);

        // resto da divisão entre o tempo e a frequencia
        float resto = mod(ui_time, frequencia);
        // porcentagem do resto no eixo x
        float x = uv2_resolution.x * (resto/frequencia);
        // diferença entre o fragmento atual e o ponto x utilizado como base
        float fragmentRelativePosition =((gl_fragCoord.x-x)/uv2_resolution.x);
        // define o ponto Y relativo ao centro
        float relativeY = uv2_resolution.y * amplitudeY * cos(radians(fragmentRelativePosition*360.0));

        float globalTimeDivFreq = floor(ui_time/frequencia);
        float posAlphaCentralX = rand(vec2(globalTimeDivFreq, float(i)));
        float sizeAlphaVariationX = 0.4;

        //posicao central y da onda
        float posY = uv2_resolution.y * (y + (fi*(height/float(quantityOfWaves))));

        // define os pontos superiores e inferiores da onda
        float bottomY = posY - relativeY;
        float upY = bottomY + grossuraY;


        float preAlpha = 0.0;

        if (frequencia > frequenciaCentral && mod(fi, 3.0) == 1.0){
            preAlpha = 0.2;
        } else {
            // define o valor alpha de acordo com a distancia do ponto em relação ao centro da onda
            float leftX = posAlphaCentralX-(sizeAlphaVariationX/2.0);
            float rightX = posAlphaCentralX+(sizeAlphaVariationX/2.0);
            float porcentagemDaFrequencia = resto/frequencia;
            float leftXOnMove = (leftX + (sizeAlphaVariationX * porcentagemDaFrequencia));
            float rightXOnMove = (rightX + (sizeAlphaVariationX * porcentagemDaFrequencia));
            float centerOnMove = leftXOnMove+((rightXOnMove - leftXOnMove)/2.0);

            if (relativePosition.x > leftXOnMove){
                if (relativePosition.x < rightXOnMove){
                    float positionAlpha = 0.0;
                    if (relativePosition.x < centerOnMove){
                        positionAlpha = (relativePosition.x - leftXOnMove)/(centerOnMove - leftXOnMove);
                    } else {
                        positionAlpha = 1.0 - ((relativePosition.x - centerOnMove)/(rightXOnMove - centerOnMove));
                    }
                    if (porcentagemDaFrequencia < 0.5){
                        preAlpha = porcentagemDaFrequencia;
                    } else {
                        preAlpha = 1.0-(porcentagemDaFrequencia);
                    }
                    preAlpha *= positionAlpha;
                }
            }
        }
        if (gl_fragCoord.y > bottomY && gl_fragCoord.y < upY){
        	if (gl_fragCoord.y < bottomY + grossuraY/2.0){
            	alpha += preAlpha * ((gl_fragCoord.y - (posY - relativeY))/grossuraY);
            } else {
                alpha += preAlpha * (1.0 -((gl_fragCoord.y - (posY - relativeY))/grossuraY));
			}
		}
    }
    */
    float multiply = 0.15;
    float alpha = 1.0;


    fragColor = vec4((multiply*alpha), (multiply*alpha), (multiply*alpha), 1.0);
}

